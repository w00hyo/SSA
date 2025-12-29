package com.sun.micro.service;

import com.sun.micro.vo.BlogEditRequestVO;
import com.sun.micro.vo.BlogListRequestVO;
import com.sun.micro.vo.BlogListResponseVO;

import java.util.List;
import java.util.Map;

public interface BlogService {
	int create(Map<String, Object> map);
	
//read ㅅ ㅣ 그 니 처
	Map<String, Object> read(int blogContSeq);
    //edit 시그니처
    boolean edit(BlogEditRequestVO blogEditRequestVO);

    //delete 시그니처
    public boolean delete(int blogContSeq);

    //리스트 나중에 콘트로러에서 서비스로직을 서비스로 실행합니다
	List<BlogListResponseVO> list(BlogListRequestVO blogListRequestVO);

	

}
