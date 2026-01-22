package com.samsung.mes.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.mes.member.dto.ProductionOrderDTO;
import com.samsung.mes.member.service.ProductionOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/production/orders")
@RequiredArgsConstructor
public class ProductionOrderController {
	
	private final ProductionOrderService service;
	
	
	@GetMapping //HTTP GET 요청이 들어오면 이 메서드 실행하라는 뜻
	public Page<ProductionOrderDTO> getOrders(
//반환타입 Page<ProductionOrderDTO>
@PageableDefault(size = 10) Pageable pageable  //파라미터: Pageable pageable
//page=0 → 첫 페이지 size=10 → 10개씩
			){
		return service.getOrders(pageable);
	}
	
	/*@GetMapping 페이징을 안쓸 경우
	public List<ProductionOrderDTO> getOrders(){
		return service.getAllOrders();
	}*/
	
	@PostMapping
	public ProductionOrderDTO createOrder(@RequestBody ProductionOrderDTO dto) {
		return service.createOrder(dto);
	}
	
	
	//상세조회
	@GetMapping("/{id}")
	public ProductionOrderDTO getOne(@PathVariable("id") Long id) {
		return service.getOne(id);
	}
		
	//수정
	@PutMapping("/{id}")
	public ProductionOrderDTO update(@PathVariable("id") Long id, @RequestBody ProductionOrderDTO dto) {
		return service.update(id, dto);
	}
	
	//삭제
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	
	
	

}
