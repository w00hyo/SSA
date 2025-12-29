package com.sun.micro.service;

import java.util.List;

import com.sun.micro.vo.CommentAddRequestVO;
import com.sun.micro.vo.CommentResponseVO;

public interface CommentService {

	CommentResponseVO add(CommentAddRequestVO commentAddRequestVO);

	//´ñ±Û 
	List<CommentResponseVO> listByBlgContSeq(int blgContSeq);

	
}
