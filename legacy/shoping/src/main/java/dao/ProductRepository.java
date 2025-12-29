package dao;

import java.util.ArrayList;

import dto.Product;

public class ProductRepository {
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();
	//신규데이터 저장 메소드
	private static ProductRepository instance = new ProductRepository();
	//메소드 1
	public static ProductRepository getInstance() {
		return instance;
	}
	
	public ProductRepository() {
	      Product house = new Product("P1234","26층 나무집",10800);
	      house.setAuthor("앤디 그리피스");
	      house.setDescription("『26층 나무 집』은 각종 상을 휩쓸며 독자들의 사랑을 받은《13층 나무 집》의 후속작으로,"
	               + " 글을 쓰는 앤디와 그림을 그리는 테리가 13층 나무 집을 ‘26층 나무 집’으로 올려 짓고 더 흥미진진한 이야기로 돌아왔다."
	               + " 범퍼카 경기장, 스케이트보드 연습장, 진흙탕 경기장, 반중력 방, 자동 판박이 기계, 78가지 맛 아이스크림 가게,"
	               + " 죽음의 미로 등 재미난 놀 거리와 맛나는 먹을거리가 새로 생겼고, 이번에도 앤디와 테리는 큰코 씨의 원고 독촉을 받는다."
	               + " 앤디가 테리와의 첫 만남을 쓰기로 마음먹자마자 테리의 더러운 팬티를 먹은 상어들이 아프면서 한바탕 난리가 나고,"
	               + " 그 와중에 이야기는 정신없이 흘러가는데…….");
	      house.setPublisher("시공주니어");
	      house.setCategory("초등");
	      house.setUnitPrice(1000);
	      house.setTotalpages(352);
	      house.setReleaseDate("2015.07.20");
	      house.setCondition("New");
	      house.setFilename("P1234.png");
	      
	      Product cris = new Product("P1235","다섯 밤만 더 자면 크리스마스",13500);
	      cris.setAuthor("지미 팰런");
	      cris.setDescription("미국 NBC‘더 투나잇 쇼’진행자 지미 팰런의 그림책\r\n"
	               + "크리스마스까지\r\n"
	               + "다섯 밤, 네 밤, 세 밤, 두 밤 그리고 하룻밤…….\r\n"
	               + "크리스마스를 기다리는 아이의 콩닥콩닥 설레임을 느껴보세요!");
	      cris.setPublisher("우리동네 책공장");
	      cris.setCategory("유아");
	      cris.setUnitPrice(900);
	      cris.setTotalpages(48);
	      cris.setReleaseDate("2018.12.24");
	      cris.setCondition("Refurbished");
	      cris.setFilename("P1235.png");
	      
	      Product python = new Product("P1236","Do it Python",9000);
	      python.setAuthor("지미 팰런");
	      python.setDescription("미국 NBC‘더 투나잇 쇼’진행자 지미 팰런의 그림책\r\n"
	               + "크리스마스까지\r\n"
	               + "다섯 밤, 네 밤, 세 밤, 두 밤 그리고 하룻밤…….\r\n"
	               + "크리스마스를 기다리는 아이의 콩닥콩닥 설레임을 느껴보세요!");
	      python.setPublisher("이지스 퍼블리싱");
	      python.setCategory("중등");
	      python.setUnitPrice(1100);
	      python.setTotalpages(268);
	      python.setReleaseDate("2020.07.28");
	      python.setCondition("E-Book");
	      python.setFilename("P1236.png");
	      
	      
	      Product dragonball = new Product("P1237","드래곤볼 슈퍼",5000);
	      dragonball.setAuthor("도리야마 아키라");
	      dragonball.setDescription("미국 NBC‘더 투나잇 쇼’진행자 지미 팰런의 그림책\r\n"
	               + "크리스마스까지\r\n"
	               + "다섯 밤, 네 밤, 세 밤, 두 밤 그리고 하룻밤…….\r\n"
	               + "크리스마스를 기다리는 아이의 콩닥콩닥 설레임을 느껴보세요!");
	      dragonball.setPublisher("점프 코믹스");
	      dragonball.setCategory("만화");
	      dragonball.setUnitPrice(5000);
	      dragonball.setTotalpages(268);
	      dragonball.setReleaseDate("2023.07.28");
	      dragonball.setCondition("E-Book");
	      dragonball.setFilename("P1237.png");
	      
	      listOfProducts.add(house);
	      listOfProducts.add(cris);
	      listOfProducts.add(python);
	      listOfProducts.add(dragonball);	      
	}
	public ArrayList<Product> getAllProducts(){
		return listOfProducts;
	}
	
	//상세 정보를 가져오는 메소드 만들기
	public Product getProductById(String bookID) {
		Product productById=null;
		
		for(int i=0; i<listOfProducts.size(); i++) {
			Product product = listOfProducts.get(i);
			if(product != null && product.getBookID() !=null && product.getBookID().equals(bookID)) {
				productById = product;
				break;
			}
		}
		return productById;
	}
	/*
	객채변수 listOfProduct에 저장된 모든 상품목록에서 북아이디로 일치하는 상품을 가져오는 productById매소드 작성
	for문을 이용하여 상품을 하나씩 가져오고 if문을 이용하여 조건에 맞는 값을 불러옴
	*/

	//메소드 2
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
	
	
	
}
