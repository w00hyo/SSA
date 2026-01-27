package com.samsung.mes.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.samsung.mes.dto.ProductionOrderDTO;
import com.samsung.mes.entity.ProductionOrder;
import com.samsung.mes.repository.ProductionOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductionOrderService {
	
private final ProductionOrderRepository repository;//Repository 주입 (DB 접근)
//👉 이 객체로 DB 조회 / 저장을 합니다.


//페이징 조회
@Transactional//중간에 에러가 나면 → 전부 취소(롤백) 다 정상적으로 끝나면 → 한 번에 확정(커밋)
public Page<ProductionOrderDTO> getOrders(int page, int size){//Pageable pageable
//그냥 List가 아니라:데이터 목록 전체 개수 전체 페이지 수 현재 페이지 번호 👉 이런 정보까지 다 포함됨
	Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
	return repository.findAll(pageable).map(this::toDto);
}

//:: -> 메서드 참조 (Method Reference)
 /*
return repository.findAll()   // 1️⃣ DB에서 전체 조회
.stream()             // 2️⃣ 리스트를 하나씩 처리
.map(this::toDto)     // 3️⃣ Entity → DTO 변환
.collect(Collectors.toList()); // 4️⃣ 다시 리스트로 
 */

	
public ProductionOrderDTO createOrder(ProductionOrderDTO dto) {
	if(repository.existsByWorkOrderNo(dto.getWorkOrderNo())) {
		throw new RuntimeException("이미 존재하는 지시번호 입니다");
	}
	ProductionOrder entity = ProductionOrder.builder() //2️⃣ DTO → Entity 변환
			.workOrderNo(dto.getWorkOrderNo())
			.orderDate(dto.getOrderDate())
			.itemCode(dto.getItemCode())
			.itemName(dto.getItemName())
			.planQty(dto.getPlanQty())
			.startDate(dto.getStartDate())
			.endDate(dto.getEndDate())
			.status(dto.getStatus() != null ? dto.getStatus() : "대기")
			//👉 상태가 없으면 기본값 "대기"
			.remark(dto.getRemark())
			.build();
			ProductionOrder saved = repository.save(entity);			
			return toDto(saved);
}
	
private ProductionOrderDTO toDto(ProductionOrder entity) {
	return ProductionOrderDTO.builder()
.id(entity.getId())
.workOrderNo(entity.getWorkOrderNo())
.orderDate(entity.getOrderDate())
.itemCode(entity.getItemCode())
.itemName(entity.getItemName())
.planQty(entity.getPlanQty())
.startDate(entity.getStartDate())
.endDate(entity.getEndDate())
.status(entity.getStatus())
.remark(entity.getRemark())
.build();
}

//상세 생산지시 1건을 id로 조회해서 DTO로 돌려주는 서비스 메서드
//“DB에서 id로 생산지시를 찾아서, 있으면 DTO로 바꿔서 리턴하고, 없으면 에러를 던진다
public ProductionOrderDTO getOne(Long id) {
//JPA Repository가 제공하는 기본 메서드 의미: “PK가 id인 데이터를 DB에서 찾아줘”
ProductionOrder e  = repository.findById(id)
.orElseThrow(() -> new NoSuchElementException("생산지시가 없습니다 .id=" + id) );
return toDto(e);
}

//수정
public ProductionOrderDTO update(Long id, ProductionOrderDTO dto) {
	ProductionOrder e = repository.findById(id)
.orElseThrow(() -> new NoSuchElementException("생산지시가 없습니다.id=" +id));
	//지시번호는 보통 수정 불가 e.setWorkOrderNo(dto.getWorkOrderNo());
	e.setOrderDate(dto.getOrderDate());
	e.setItemCode(dto.getItemCode());
	e.setItemName(dto.getItemName());
	e.setPlanQty(dto.getPlanQty());
	e.setStartDate(dto.getStartDate());
	e.setEndDate(dto.getEndDate());
	e.setStatus(dto.getStatus());;
	e.setRemark(dto.getRemark());;
	
	return toDto(repository.save(e));
}

//삭제
public void delete(Long id) {
	if(!repository.existsById(id)) {
		throw new NoSuchElementException("생산지시가 없습니다. id=" + id);
	}
	repository.deleteById(id);
}



}

/*
초기학습에 연습용

public List<ProductionOrderDTO> getAllOrders(){//전체 생산지시 조회
 return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
*/







