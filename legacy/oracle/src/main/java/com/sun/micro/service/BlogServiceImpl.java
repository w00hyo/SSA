package com.sun.micro.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.micro.dao.BlogDAO;
import com.sun.micro.mapper.BlogMapper;
import com.sun.micro.vo.BlogEditRequestVO;
import com.sun.micro.vo.BlogListRequestVO;
import com.sun.micro.vo.BlogListResponseVO;

@Service
@MapperScan("com.sun.micro.mapper")
public class BlogServiceImpl implements BlogService{
	
	private BlogDAO blogDAO;
    private BlogMapper blogMapper;


    @Autowired //생성자 주입
    public BlogServiceImpl(BlogDAO blogDAO, BlogMapper blogMapper){
        this.blogDAO = blogDAO;
        this.blogMapper = blogMapper;
    }
    /*
생성자 public BlogServiceImpl(BlogDAO blogDAO)

이 부분은 생성자 주입(Constructor Injection) 입니다.
Spring에서 DI를 할 때는 다음 세 가지 방식이 있습니다.

방식	설명	예시
생성자 주입	생성자를 통해 의존 객체를 주입
✅ @Autowired 생성자 방식

세터 주입	setter 메서드를 통해 주입
@Autowired setter 방식
필드 주입	필드에 직접 @Autowired
@Autowired private BlogDAO blogDAO;
    * */
    /*
    @Autowired 어노테이션
Spring Framework의
의존성 주입(Dependency Injection, DI) 기능을 사용하는
어노테이션입니다.
@Autowired를 붙이면 Spring이 자동으로
해당 파라미터나 필드에 맞는 객체(Bean)를 주입해줍니다.
즉, BlogServiceImpl 객체를 만들 때
Spring이 BlogDAO 타입의 객체를
자동으로 찾아서 전달합니다.
    */


	@Override
	public int create(Map<String, Object> map) {
		int seq = this.blogDAO.insert(map);
		return seq;
	}


	@Override //부모 클래스나 인터페이스에 매서드를 재정의 한다는 의미
	//서비스구현 클래스에 read메서드 구현
	public Map<String, Object> read(int blogContSeq) {
Map<String, Object> blogCont = this.blogDAO.selectOne(blogContSeq);
		return blogCont;
	}

    @Override
    public boolean edit(BlogEditRequestVO blogEditRequestVO){//참 거짓
        int affectRowsCount = this.blogDAO.update(blogEditRequestVO);
        // 영향을 받은(수정된) 행(row)의 개수를 반환
        return affectRowsCount > 0;
        //수정된 행의 개수가 0보다크면 true
    }

    //delete
    @Override
    public boolean delete(int blogContSeq){
        return this.blogMapper.delete(blogContSeq) > 0;
    }
    
    //list
    @Override//해당메서드가 인터페이스나 슈퍼클래스에 선언된 메서드를 재정의 한다는걸 나타냅니다
    public List<BlogListResponseVO> list(BlogListRequestVO blogListRequestVO){
    	//매서드 서명 반환유형 List<BlogListResponseVO> 매개변수 BlogListRequestVO blogListRequestVO
    	List<BlogListResponseVO> result = this.blogMapper.selectList(blogListRequestVO);
    	//매퍼메서드를 호출 MyBatis매퍼 입니다
    	return result;
    	//검색된 게시물 목록을 API계층으로 반환합니다
    }


    
    
    
    
    
    
    
    
    
    
    
    
    

}
