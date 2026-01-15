package port.sm.erp.service;

import java.time.LocalDate;
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
	
	
	
	@Transactional(readOnly = true) //이메서드는 조회 전용
	public List<CalendarEvent> list (Long userId, LocalDate from , LocalDate to){
		
		List<CalendarEvent> mine = repo.findByUserIdAndDateBetween(userId, from, to);
		//db에서 내 userId + 날짜 범위로 조회
		
		List<CalendarEvent> shared = repo.findSharedWithUser(userId, from, to);
		
	}

}
