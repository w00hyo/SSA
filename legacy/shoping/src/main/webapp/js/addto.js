function addToCart(){
	if(confirm('상품을 장바구니에 추가해 주시겠습니까?')){
		document.addForm.submit();		
	}else{
		document.addForm.reset();
	}
}