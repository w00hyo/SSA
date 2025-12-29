package com.sun.micro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sun.micro.vo.CommentInsertVO;
import com.sun.micro.vo.CommentResponseVO;

@Mapper //전에 다오라는걸 쓰다가 vo 를 만들고 요청과 응답을 따로줌
public interface CommentMapper {

	int insert(CommentInsertVO commentInsertVO);

	//댓글목록 매퍼
	List<CommentResponseVO> selectListByBlgContSeq(int blgContSeq);
}
