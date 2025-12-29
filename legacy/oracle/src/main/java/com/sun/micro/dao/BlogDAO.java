package com.sun.micro.dao;

import java.util.Map;

import com.sun.micro.vo.BlogEditRequestVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;//싱글톤패턴 프로그램 ㅈ ㅓ ㄴ ㅊ ㅔ ㅐ ㅔ 서 단하나의 인스턴스만 존재하도록 보장하는 패턴

	//insert
	public int insert(Map<String, Object> map) {
		//map SQL에 바인딩될 파라미터로 전달 됩니다
		int result = this.sqlSession.insert("TB_BLG_CONT.insert", map);
		//숫자형변수 result 에 SqlSessionTemplate 을 사용해서 TB_BLG_CONT.insert라는 SQL매퍼 구문을 실행합니다
		if (result > 0 && map.containsKey("seq_blg_cont")) {
			
			return (int)map.get("seq_blg_cont");//지나가는 예니킴에 질문에 대한 answer 반환값 타입이 오브젝트 이기 때문에..
			//마리떼 프랑소와  java는 자동으로 Object -> int 를 할수가 없음 
			/*
			if (result > 0 && map.containsKey("seq_blg_cont"))
			insert가 성공했을 때(result > 0),
			그리고 map에 "seq_blg_cont"라는 키가 포함되어 있을 때 (보통 DB에서 생성된 시퀀스 값이나 자동 증가 키가 매핑되어 들어옵니다),
			그 값을 꺼내서 반환합니다.
			
			*incompatible types: Object cannot be converted to int
			*/
			
		}
		return -1;
		/*
		정상적으로 insert가 성공하면 1
		실패하거나 아무 행도 삽입되지 않으면 0
		그외 seq_blg_cont가 없는경우 -1
		 * */
	}

	//read
	public Map<String, Object> selectOne(int blogContSeq) {
		// DB에서 단일 게시글을 읽어오는 실제 SQL실행 단계
		return this.sqlSession.selectOne("TB_BLG_CONT.selectOne", blogContSeq);
	}

    //edit 업데이트로 구성된 MyBatis데이터 액세스 객체 행의수를 리턴하는 메서드
    public int update(BlogEditRequestVO blogEditRequestVO){
        return this.sqlSession.update("TB_BLG_CONT.update", blogEditRequestVO);
    }
	
	
	
	
	
	
	
	

}

