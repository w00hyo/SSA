package com.sm.kr.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sm.kr.dto.BootCreateDTO;
import com.sm.kr.dto.BootEditDTO;
import com.sm.kr.dto.BootEditResponseDTO;
import com.sm.kr.dto.BootListResponseDTO;
import com.sm.kr.dto.BootReadResponseDTO;
import com.sm.kr.entity.Boot;
import com.sm.kr.entity.BootRepository;

@Service //이클래스가 서비스 컴포넌트임을 나타내고 
//spring이 이 클래스를 Bean으로 등록하여 DI(의존성 주입)등을 가능하게 해줌
public class BootService {
	
	//BootRepository를 생성자로 주입받음{생성자 기반 의존성 주입}
	//jpa리파지토리 db에 저장 /조회 기능 수행
	//why 생성자 1)bootRepository를 BootService DB작업을 하면 
//bootRepository는 우리가 직접 객체로 만들지 않고 
	//Spring 관리하는건 빈
	// 스프링이 자동으로 주입해줄수 잇게 하기위해 해주는 것이 생성자 주입입니다
	
	private BootRepository bootRepository;
	
	
	//BootService 생성자 만듬 BootRepository를 매개변수로 생성자 주입
	public BootService(BootRepository bootRepository) {
		this.bootRepository = bootRepository;
	}
	
	//비즈니스로직 insert
	public Integer insert(BootCreateDTO bootCreateDTO) {
		Boot boot = Boot.builder().
		title(bootCreateDTO.getTitle())
		.price(bootCreateDTO.getPrice())
		.build();
		this.bootRepository.save(boot);	//데이터 베이스 한줄을 저장소에 저장 	

		return boot.getBootId();
	}
	
	//비즈니스로직 read
	public BootReadResponseDTO read(Integer bootId) throws NoSuchElementException{
/*
BootReadResponseDTO : 리턴 타입 (클라이언트에 응답할 객체)
read(Integer bootId) :파라미터로 하나의 id를 받음
throws NoSuchElementException : 해당 id가 없으면 예외발생
*/
		Boot boot = this.bootRepository.findById(bootId).orElseThrow(() -> new EntityNotFoundException(" 글에 순번을 찾을수 없습니다" + bootId ));
//커맨드 부트에 = 현재클래스에 리파지토리객체(bootRepository)에서 pk을 찾거나 예외가 발생되거나 findById jpa에서 제공하는 메서드
//orElseThrow() 만약 안에 값이 null ji tae 라면 예외를 던집니다
		//이줄이 가능하려면 @NoArgsConstructor
		BootReadResponseDTO bootReadResponseDTO = new BootReadResponseDTO();//객체 생성
		//DTO 반환 클라이언트나 컨트롤러로 응답 DTO객체를 리턴
		bootReadResponseDTO.fromBoot(boot);
		//Entity를 직접리턴하지 않고 DTO변환하는 이유는 보안, 데이터 캡슐화, 성능최적화 때문입니다
		
		return bootReadResponseDTO;
		
		
	}
	
/*
Java Persistence API : 객체와 관계형 데이터베이스 (RDB)를 매핑해주는 표준 Object Relation Mapping API입니다
자바클래스 - 데이터베이스 테이블 맵핑
자바객체 - 테이블 행(row)

jpa를 사용하는 이유 
생산성 : SQL을 직접 쓰는 양이 줄어듬 (Entity, Repository 등으로 처리가 가능하다)
유지보수성 : 객체 지향적인 코드 유지 가능
이식성 : DB벤더(Oracle, MySQL, postgre.., h2)교체가 쉬움
성능 : 1차 캐시, 지연 로딩, 벌크 연산 등 다양한 최적화 기능을 제공
표준화 : 자바진영에서(오라클) 공식적으로 정한 표준 ORM API
*/	
	
	//수정 
	/*
	접근제어자 public : 외부에서 호출가능
	반환타입 : BootEditResponseDTO 수정 화면등에 사용할수 있는 데이터 DTO객체를 리턴
	메서드이름 : edit 수정을 시작할때 호출하는 메서드
	매개변수 : Integer bootId 조회하고자하는 순번id
	예외처리 : throws NoSuchElementException 주어진 id에 순번을 찾지못하면 발생
	글을 수정하기위해 수정할 글을 저장소에서 불러옴
	*/
	public BootEditResponseDTO edit(Integer bootId) throws NoSuchElementException{
		Boot boot = this.bootRepository.findById(bootId).orElseThrow();
		return BootEditResponseDTO.BootFactory(boot);
	}
	//수정하는 서비스로직을 전달 (파라미터 : 수정할 정보를 담고 있는 DTO)
	public void update(BootEditDTO bootEditDTO)throws NoSuchElementException{
		Boot boot = this.bootRepository.findById(bootEditDTO.getBootId()).orElseThrow();
		boot = bootEditDTO.fill(boot);
		//보통 fill()메서드는  DTO의 값을 엔티티에 덮어쓰는 식으로 구현되어 있습니다
		this.bootRepository.save(boot);
	}
	
	//삭제 기능 로직
	public void delete(Integer bootId) throws NoSuchElementException{
		Boot boot = this.bootRepository.findById(bootId).orElseThrow();
		this.bootRepository.delete(boot);
	}
	
	
	//list
	public List<BootListResponseDTO> bootList(String title, Integer page){
		
		final int pageSize = 3;//불변으로 만들때 한페이지에 보여줄 개수를 3개로 설정
		
		List<Boot> boots;
		
		//page가 널인 경우 0페이지로 설정(즉 첫페이지)
		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
		
		if (title == null) {//title이 null이면 전체목록을 가져옴 검색 x
			// 전체 리스트를 최신순(내림차순)으로 페이징 조회
			
//페이지번호(page), 페이지크기(pageSize), 정렬기준(insertDateTime 내림차순) 으로 페이징 설정 
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "insertDateTime");
			/**/boots = this.bootRepository.findAll(pageable).toList();		
		
		}else {//title 이 null이 아니면			
			Sort sort = Sort.by(Order.desc("insertDateTime"));
			Pageable pageable = PageRequest.of(page, pageSize, sort);
			boots = this.bootRepository.findByTitleContains(title, pageable);
		}
		
		return boots.stream().map(
				boot -> new BootListResponseDTO(boot.getBootId(), boot.getTitle())
		).collect(Collectors.toList());
		
		/*
		.stream() 은 컬렉션을  Stream으로 변환합니다
		Stream은 데이터를 순차적으로 처리할수 있는 java의 기능으로 함수형 스타일의 프로그래밍을 가능하게 해줍니다
		map()은 스트림에 각요소를 변환하는데 사용합니다
		collect => 변환된 스트림 요소들을 List로 수집합니다 
		결과적으로 List<BootListResponseDTO>가 반환됩니다
		*/
	}	
	
}//end
