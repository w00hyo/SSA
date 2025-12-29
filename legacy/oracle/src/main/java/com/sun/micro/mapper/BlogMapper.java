package com.sun.micro.mapper;

import java.util.List;
import com.sun.micro.vo.BlogListRequestVO;
import com.sun.micro.vo.BlogListResponseVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
	//삭제
    int delete(int blgContSeq);
    
    //리스트
    List<BlogListResponseVO> selectList(BlogListRequestVO blogListRequestVO);

}
