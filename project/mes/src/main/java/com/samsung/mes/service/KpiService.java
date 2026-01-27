package com.samsung.mes.member.service;

import com.samsung.mes.member.dto.KpiRequest;
import com.samsung.mes.member.dto.KpiResponse;
import com.samsung.mes.member.entity.Kpi;
import com.samsung.mes.member.repository.KpiRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KpiService {

    private final KpiRepository repo;

    //필수로 기억 모든 리스트는 페이징
    public Page<KpiResponse> list (Pageable pageable) {//목록조회 함수
        return repo.findAll(pageable).map(this::toRes);
        //Pageable pageable: 몇 페이지를 볼지”, “한 페이지에 몇 개를 가져올지”, “정렬은 어떻게 할지
        //반환 타입 Page<KpiResponse>:
        //repo.findAll(pageable) DB에서 KPI를 페이징 적용해서 조회해요.
        //.map(this::toRes) 페이지 정보(총 개수, 현재 페이지 등)는 그대로 유지되고, content 안의 요소만 DTO로 변환돼요.
        //:: 메서드 참조(Method Reference) 라는 문법이에요. 람다식을 짧고 읽기 쉽게 쓰는 방법
    }


    public KpiResponse getOne(Long id) { //한건을 id로 조회
        Kpi e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("KPI가 없습니다.id=" + id));
        return toRes(e);
    }

    @Transactional //이 메서드 안에서 일어나는 DB 작업을 하나의 묶음(트랜잭션) 으로 처리
    //✅ 중간에 문제 없이 끝나면 → 저장 확정(커밋) ❌ 중간에 에러가 나면 → 저장 취소(롤백)
    public KpiResponse create(KpiRequest req){ //KpiRequest req : 프론트에서 넘어온 등록용 데이터(입력값)
        Kpi e = new Kpi();//DB 테이블 한 줄(레코드)과 연결된 객체예요.b
        applyReq(e, req);//입력값을 엔티티에 채워 넣는 단계예요.
        return toRes(repo.save(e));//JPA Repository가 DB에 저장하는 명령이에요.
    }

    @Transactional
    public KpiResponse update(Long id, KpiRequest req){
        Kpi e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("KPI가 없습니다 . id" + id));
        applyReq(e, req);
        return toRes(e);
    }

    @Transactional
    public void delete(Long id){
        if(!repo.existsById(id)){
            throw  new NoSuchElementException("KPI가 없습니다.id=" + id);
        }
        repo.deleteById(id);
    }

    //매핑 create / update에서 중복 코드 제거
    private void applyReq(Kpi e, KpiRequest req) {
        e.setKpiName(req.getKpiName());
        e.setKpiGroup(req.getKpiGroup());
        e.setOwner(req.getOwner());
        e.setPeriodType(req.getPeriodType());
        e.setPeriodValue(req.getPeriodValue());
        e.setTargetValue(req.getTargetValue());
        e.setActualValue(req.getActualValue());
        e.setUnit(req.getUnit());
        e.setStatus(req.getStatus());
        e.setUseYn(req.getUseYn());
        e.setRemark(req.getRemark());
        // ✅ updatedAt / 기본값은 @PrePersist/@PreUpdate가 처리
    }

    private KpiResponse toRes(Kpi e) {
        return KpiResponse.builder()
                .id(e.getId())
                .kpiName(e.getKpiName())
                .kpiGroup(e.getKpiGroup())
                .owner(e.getOwner())
                .periodType(e.getPeriodType())
                .periodValue(e.getPeriodValue())   // ✅ 네 엔티티에 필수라서 포함 추천
                .targetValue(e.getTargetValue())
                .actualValue(e.getActualValue())
                .unit(e.getUnit())
                .status(e.getStatus())
                .useYn(e.getUseYn())
                .remark(e.getRemark())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

}
