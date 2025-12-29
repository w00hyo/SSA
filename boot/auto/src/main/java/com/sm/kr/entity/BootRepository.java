package com.sm.kr.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootRepository extends JpaRepository<Boot, Integer> {

	//add 타이틀을 기준으로 페이징을 만들기 위해서
	public List<Boot> findByTitleContains(String title, Pageable pageable);
	/*
	1) 소속 : Repository 인터페이스에서 정의됩니다
	2) 메서드 구성 분석 :
	- findBy : JPA에서 데이터를 조회하는 메서드 임을 의미
	- TitleContains : Boot 엔티티에 있는 title 필드기준으로 조건을 지정
	(LIKE %value% 와 유사합니다) 즉 주어진 문자열이 title에 포함되어 있는지를 검사합니다
	- SELECT * FROM boot WHERE title LIKE '%Java%'
	- Pageable pageable : 페이징과 정렬을 위한 파라미터 (int page, int size, Sort sort)
	등으로 생성합니다
	
	3) 반환타입 : List<Boot> => 페이징된 Boot객체들의 리스트
	
	4) 사용예시 :
	Pageable pageable = PageRequest.of(0, 5, Sort.by("title").descending());
	List<Boot> boots = bootRepository.findByTitleContains("Spring",pageable);
	
	
	
	
	
	
	*/
	
	
}
