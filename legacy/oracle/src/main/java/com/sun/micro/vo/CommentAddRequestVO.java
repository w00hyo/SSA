package com.sun.micro.vo;

//이미 댓글을 저장하기 위해 필요한 CommentInsertVO를 작성했음에도 불구하고
//요청을 전담하는 vo를 추가로 만들었습니다 
//이는 데이터 저장을 위한 항목과 요청을 받기 위한 항목이 다르기 때문입니다
/*
CommentInsertVO에는 데이터베이스에 값을 입력하고
난 후의 pk저장하기 위한  seqBlgCmt멤버변수가 있다
하지만 사용자가 웹어플리케이션에 요청할때는 필요도 없고 입력할수도 없는
항목입니다 그래서 요청에 쓰는 VO 저장할때 사용하는 VO
*/
public class CommentAddRequestVO {
	private int blgContSeq;
	private String cmtBdy, tmpPw;
	public int getBlgContSeq() {
		return blgContSeq;
	}
	public void setBlgContSeq(int blgContSeq) {
		this.blgContSeq = blgContSeq;
	}
	public String getCmtBdy() {
		return cmtBdy;
	}
	public void setCmtBdy(String cmtBdy) {
		this.cmtBdy = cmtBdy;
	}
	public String getTmpPw() {
		return tmpPw;
	}
	public void setTmpPw(String tmpPw) {
		this.tmpPw = tmpPw;
	}
	
	

}
