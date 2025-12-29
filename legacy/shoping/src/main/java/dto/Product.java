package dto;

import java.io.Serializable;

public class Product implements Serializable {
//extends는 클래스 확장,implements는 인터페이스 구현
	private static final long serialVersionUID = -4274700572038677000L;
	//자바에서 객체를 직렬화 하여 바이트스트림으로 만들어 저장한다 직렬화와 역직렬화를 할때 이값으로 해당클래스의
	//특정버전에 맞는지 아닌지를 판단하겠다는것 serialVersionUID가 선언되지 않으면 default값으로 처리하는데
	//이럴경우 역직렬화시 얘기치 못하게 실패할수 있다
	private String bookID;//아이디
	private String bookName;//상품명
	private Integer unitPrice;//상품가격
	private String author;//저자
	private String description;//설명
	private String category;//분류
	private String publisher;//출판사
	private long unitsInStock;//재고수
	private long totalpages;//페이지수
	private String releaseDate;//출판일
	private String condition;//신규중고
	private String filename;//이미지 파일명
	private int quantity;//장바구니에 담은갯수
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product() {
		super();//부모의 메소드 변수에 접근하기 위해서 사용
	}
	public Product(String bookID, String bookName, Integer unitPrice) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.unitPrice = unitPrice;
	}
	
	
	
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public long getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public long getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(long totalpages) {
		this.totalpages = totalpages;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}	

}
