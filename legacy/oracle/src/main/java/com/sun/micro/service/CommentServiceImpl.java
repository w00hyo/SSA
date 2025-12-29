package com.sun.micro.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sun.micro.mapper.CommentMapper;
import com.sun.micro.vo.CommentAddRequestVO;
import com.sun.micro.vo.CommentInsertVO;
import com.sun.micro.vo.CommentResponseVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	//肄붾찘�듃 留대쾭 蹂��닔�꽑�뼵
	private CommentMapper commentMapper;
	
	//�깮�꽦�옄 二쇱엯
	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	//
	@Override
	public CommentResponseVO add(CommentAddRequestVO commentAddRequestVO) {
		ModelMapper modelMapper = new ModelMapper();//pom di
		CommentInsertVO commentInsertVO = modelMapper.map(commentAddRequestVO, CommentInsertVO.class);
		
		if(commentInsertVO.getTmpPw() != null) {//留뚯빟 �엫�떆鍮꾨�踰덊샇媛� 鍮꾩뼱�엳吏� �븡�떎硫� 
			//�엫�떆鍮꾨�踰덊샇瑜� �븫�샇�솕 �빀�땲�떎
			String sha256hex = DigestUtils.sha256Hex(commentInsertVO.getTmpPw());
			//DigestUtils.sha256Hex 硫붿냼�뱶媛� 臾몄옄�뿴�쓣 SHA256�빐�떆 �븣怨좊━利섏쓣 �넻�빐 SHA256媛믪쑝濡� 蹂�寃쏀븳�떎
			commentInsertVO.setTmpPw(sha256hex);
			//Secure Hash Algorithm  256-bit
		}
		int affectRowCount = this.commentMapper.insert(commentInsertVO);
		
		if (affectRowCount == 0) {
			return null;
		}
		
		// �젙�긽�쟻�쑝濡� �뜲�씠�꽣 �엯�젰�씠 �꽦怨듯뻽�떎硫�..
		ModelMapper modelMapperInsertToResponse = new ModelMapper();
		
CommentResponseVO commentResponseVO = modelMapperInsertToResponse.map(commentInsertVO, CommentResponseVO.class);
		
		return commentResponseVO;
	}
	
	//댓글목록서비스구현클래스
	@Override
	public List<CommentResponseVO> listByBlgContSeq(int blgContSeq){
		
		return this.commentMapper.selectListByBlgContSeq(blgContSeq);
		
	}
	
	
	
	
	
	
	
	
	
	

}
