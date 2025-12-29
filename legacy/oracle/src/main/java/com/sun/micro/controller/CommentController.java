package com.sun.micro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.micro.service.CommentService;
import com.sun.micro.vo.CommentAddRequestVO;
import com.sun.micro.vo.CommentResponseVO;

@Controller
@RequestMapping("/comment") 
//�겢�옒�뒪�쐞�뿉 �꽑�뼵 �릺硫� �빐�떦肄섑듃濡ㅻ윭 �겢�옒�뒪�쓽 紐⑤뱺 �슂泥��쓽 �젒�몢�뼱(prefix)媛� �맗�땲�떎
//怨듯넻二쇱냼媛� �뱾�뼱媛덈븣 �븵遺�遺꾩쓣 泥섎━�븷�븣
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/add")
	@ResponseBody
	public CommentResponseVO add (CommentAddRequestVO commentAddRequestVO) {
		return this.commentService.add(commentAddRequestVO);
	}
	
	//댓글
	@GetMapping("/list/{blgContSeq}")
	@ResponseBody //json 반환하려고 하는데 리스판스 바디를 하지 않으면 스프링이 view name으로 리스트 객체를 해석하려고 시도해서 500이 발생
	public List<CommentResponseVO> listByBlgContSeq(@PathVariable("blgContSeq") int blgContSeq){
		return this.commentService.listByBlgContSeq(blgContSeq);
	}
	
	
	
	
	
	
	

}
