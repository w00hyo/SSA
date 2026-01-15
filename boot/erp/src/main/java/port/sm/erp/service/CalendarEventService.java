package port.sm.erp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import port.sm.erp.entity.CalendarEvent;
import port.sm.erp.repository.CalendarEventRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //이메서드는 조회 전용입니다
public class CalendarEventService {
	//목적 캘린더 월간/주간 화면에서 필요한 기간(from ~ to) 일정 목록을 만듬
	//내일정 (mine) 공유받은 일정 둘다 포함
	private final CalendarEventRepository repo;
	
	
	
	@Transactional(readOnly = true)
    public List<CalendarEvent> list(Long userId, LocalDate from, LocalDate to) {
        List<CalendarEvent> mine = repo.findByUserIdAndDateBetweenOrderByDateAsc(userId, from, to);

        // 공유자에 userId가 포함된 일정도 같이 포함 (읽기 전용 정책)
        List<CalendarEvent> shared = repo.findSharedWithUser(userId, from, to);

        // 중복 제거(혹시 내 일정이 shared에도 걸릴 경우)
        // id 기준으로 합치기
        List<CalendarEvent> merged = new ArrayList<>(mine);
        for (CalendarEvent ev : shared) {
            boolean exists = merged.stream().anyMatch(m -> m.getId().equals(ev.getId()));
            if (!exists) merged.add(ev);
        }

        // 정렬: date -> startTime(문자열 HH:mm) 기준 (startTime 없으면 뒤로)
        merged.sort((a, b) -> {
            int c = a.getDate().compareTo(b.getDate());
            if (c != 0) return c;
            String as = a.getStartTime() == null ? "99:99" : a.getStartTime();
            String bs = b.getStartTime() == null ? "99:99" : b.getStartTime();
            return as.compareTo(bs);
        });

        return merged;
    }
	
	/*
	 * 
	*/
	@Transactional(readOnly = true) //조회 전용 트랜잭션으로 실행하겠다는 뜻
//JPA(하이버네이트)가 변경감지(Dirty checking)을 최소화 하고 flush를 안하도록 해서 조회/성능 안정성을 올려줌
//jpa명세를 실제로 구현한 가장대표적인 ORM jpa가 무엇을 할지 정의하면 하이버네이트가 구체적으로 어떻게 할지 처리
//개발자는 sql대신 자바 객체코드로 데이터 베이스 작업을 할수 잇게 해줍니다
	public CalendarEvent get(Long userId, Long id)  {//현재 로그인한 사용자(요청자)
		//먼저 db에서 일정 존재 여부 확인 
CalendarEvent ev = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다 id " +id));
	//pk로 일정 하나를 조회하고 반환타입은 Optional ? id가 없으면 결과가 없을수도 있으니까..
//orElseThrow 값이 잇으면 그, 값을 꺼내고 (ev에 담고) 값이 없으면 예외를 던져거 메서드를 즉시 종료
//일정 x 400급 에러 (IllegalArgumentException)로 end 있으면 ev에 담아

//아래 이사용자가 이 일정을 읽을수 있을까
if(!canRead(userId, ev)) {
throw new SecurityException("권한이 없습니다");
}
return ev;
}
	
	//일정생성 userId:현재 로그인한 사용자의 id, body 프론트에서 보낸 일정 데이터
public CalendarEvent create(Long userId, CalendarEvent body) {

	body.setId(null); //id는 클라이언트가 정하면 안된다 일정 pk(id)는 db에서 자동생성해야 되는데
	//프론트에서 잘못보내면 일정이 덮어쒸어 지거나 오류가 날수 있어서
	body.setUserId(userId);//로그인 정보에서 나온 userId를 서버가 직접 넣어야 안전
	validateRequired(body);//DB에 넣고 나서 터지는것 보다 이게 나아서 검사
	
	if (body.getAttendees() == null) body.setAttendees(new ArrayList<>());
	if (body.getSharers() == null) body.setSharers(new ArrayList<>());
	//없음 비어있음
	
	return repo.save(body); //저장
}
	//일정수정
public CalendarEvent update(Long userId, Long id, CalendarEvent body) {
CalendarEvent ev = repo.findByIdAndUserId(id, userId).orElseThrow(
		() -> new SecurityException("수정 권한이 없거나 일정이 존재하지 않습니다 .id" + id));


//덮어쓰기 필드별
ev.setDate(body.getDate());
ev.setTitle(body.getTitle());
ev.setMemo(body.getMemo());

ev.setStartTime(body.getStartTime());
ev.setEndTime(body.getEndTime());

ev.setCategory(body.getCategory());
ev.setCalendar(body.getCalendar());
ev.setLocation(body.getLocation());
ev.setLabel(body.getLabel());

//jpa엔티티안의 리스트 컬렉션을 새 리스트로 갈아 끼우지 말고 
//기존 리스트를 유지한테 내용만 교체하려는 패턴 컬렉션은 교체(중요)
ev.getAttendees().clear(); //기존 리스트 객체는 그대로 두고 안에 들어있는 요소만 전부 삭제
if(body.getAttendees() != null) ev.getAttendees().addAll(body.getAttendees());
//프론트에서 넘어온 body.attendees가 null이 아니면 방금 비워둔 ev.attendees에 새 참석자 목록 전부 추가
//결과적으로 ev.attendees는 요청값으로 완전히 덮어쓴 상태가 됨
//null이면 비워진 상태 유지 (참석자 없음)

ev.getSharers().clear();//다비움 기존 컬렉션 유지 + 내용만 덮기
if(body.getSharers() != null) ev.getSharers().addAll(body.getSharers());

validateRequired(ev);

return ev;

}

	//일정삭제
public void delete(Long userId, Long id) {
CalendarEvent ev = repo.findByIdAndUserId(id, userId).orElseThrow(()-> new SecurityException("삭제 권한이 없거나 일정이 존재하지 않습니다 id" + id));
}

	
	//내부 유틸
	private boolean canRead(Long userId, CalendarEvent ev) {
		//내가 만든 일정이면 OK
		if(ev.getUserId() != null && ev.getUserId().equals(userId)) {
			return true;
		}
		//공유자에 포함되어 있으면 읽기 허용
		if(ev.getSharers() != null && ev.getSharers().contains(String.valueOf(userId))) {
			return true;
		}
				
		return false;
	}

	private void validateRequired(CalendarEvent ev) {
		if(ev.getDate() == null) {
			throw new IllegalArgumentException("date는 필수입니다");
		}
		if(ev.getTitle() == null || ev.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException("title은 필수 입니다");
		}
	}
	
	
	
	
	
	
	
	


}
