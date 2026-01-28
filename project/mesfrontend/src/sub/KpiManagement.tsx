import { useEffect, useState } from "react";
import Lnb from "../include/Lnb";
import Top from "../include/Top";

import { Wrapper, DflexColumn, Content, Ctap } from "../styled/Sales.styles";
import { SpaceBetween, Center, Dflex, PageTotal } from "../styled/Component.styles";

import { Container, Row, Col, Table, Button, Modal, Form, Pagination } from "react-bootstrap";

import * as XLSX from "xlsx";
import { saveAs } from "file-saver";

const API_BASE = "http://localhost:9500";

type KpiItem = {
  id: number;
  kpiName: string;
  kpiGroup?: string;
  owner?: string;
  periodType: "MONTH" | "QUARTER" | "YEAR";
  periodValue: string;
  targetValue: number;
  actualValue: number;
  unit?: string;
  status?: "ON_TRACK" | "RISK" | "OFF_TRACK";
  useYn: "Y" | "N";
  remark?: string;
};

type PageResponse<T> = {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
};

const TABLE_HEADERS: { key: keyof KpiItem; label: string }[] = [
  { key: "kpiName", label: "KPI명" },
  { key: "kpiGroup", label: "그룹" },
  { key: "owner", label: "담당자" },
  { key: "periodType", label: "기간유형" },
  { key: "periodValue", label: "기간" },
  { key: "targetValue", label: "목표" },
  { key: "actualValue", label: "실적" },
  { key: "unit", label: "단위" },
  { key: "status", label: "상태" },
  { key: "useYn", label: "사용여부" },
  { key: "remark", label: "비고" },
];

const KpiManagement = () => {
  const [rows, setRows] = useState<KpiItem[]>([]);
  const [page, setPage] = useState(0);
  const [size] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);

  const [showCreate, setShowCreate] = useState(false);
  const [showDetail, setShowDetail] = useState(false);
  const [selected, setSelected] = useState<KpiItem | null>(null);

  const [createForm, setCreateForm] = useState({
    kpiName: "",
    kpiGroup: "",
    owner: "",
    periodType: "MONTH" as "MONTH" | "QUARTER" | "YEAR",
    periodValue: "",
    targetValue: "",
    actualValue: "",
    unit: "",
    status: "ON_TRACK" as "ON_TRACK" | "RISK" | "OFF_TRACK",
    useYn: "Y" as "Y" | "N",
    remark: "",
  });

  const [editForm, setEditForm] = useState({ ...createForm });

  const onCreateChange = (e: React.ChangeEvent<any>) => {
    const { name, value } = e.target;
    setCreateForm((prev) => ({ ...prev, [name]: value }));
  };

  const onEditChange = (e: React.ChangeEvent<any>) => {
    const { name, value } = e.target;
    setEditForm((prev) => ({ ...prev, [name]: value }));
  };

  const fetchList = async (p: number) => {
    const res = await fetch(`${API_BASE}/api/kpis?page=${p}&size=${size}`);
    if (!res.ok) return;

    const data: PageResponse<KpiItem> = await res.json();
    setRows(data.content);
    setTotalPages(data.totalPages);
    setTotalElements(data.totalElements);
  };

  useEffect(() => {
    fetchList(page);
  }, [page]);

  const goPage = (p: number) => {
    const next = Math.max(0, Math.min(p, totalPages - 1));
    setPage(next);
  };

  const handleExcelDownload = () => {
    const excelData = [
      ["#", ...TABLE_HEADERS.map((h) => h.label)],
      ...rows.map((r, i) => [
        i + 1 + page * size,
        r.kpiName,
        r.kpiGroup ?? "",
        r.owner ?? "",
        r.periodType,
        r.periodValue,
        r.targetValue,
        r.actualValue,
        r.unit ?? "",
        r.status ?? "",
        r.useYn,
        r.remark ?? "",
      ]),
    ];

    const ws = XLSX.utils.aoa_to_sheet(excelData);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "KPI관리");

    const file = XLSX.write(wb, { bookType: "xlsx", type: "array" });
    saveAs(new Blob([file]), "KPI관리.xlsx");
  };

  const handleSave = async () => {
    const res = await fetch(`${API_BASE}/api/kpis`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        ...createForm,
        targetValue: Number(createForm.targetValue || 0),
        actualValue: Number(createForm.actualValue || 0),
      }),
    });

    if (!res.ok) {
      alert("등록 실패");
      return;
    }

    setShowCreate(false);
    fetchList(page);
    setCreateForm({
      kpiName: "",
      kpiGroup: "",
      owner: "",
      periodType: "MONTH",
      periodValue: "",
      targetValue: "",
      actualValue: "",
      unit: "",
      status: "ON_TRACK",
      useYn: "Y",
      remark: "",
    });
  };

  const openDetail = async (id: number) => {
    const res = await fetch(`${API_BASE}/api/kpis/${id}`);
    if (!res.ok) return;

    const data: KpiItem = await res.json();
    setSelected(data);
    setEditForm({
      kpiName: data.kpiName,
      kpiGroup: data.kpiGroup ?? "",
      owner: data.owner ?? "",
      periodType: data.periodType,
      periodValue: data.periodValue,
      targetValue: String(data.targetValue),
      actualValue: String(data.actualValue),
      unit: data.unit ?? "",
      status: data.status ?? "ON_TRACK",
      useYn: data.useYn,
      remark: data.remark ?? "",
    });
    setShowDetail(true);
  };

  const handleUpdate = async () => {
    if (!selected) return;

    const res = await fetch(`${API_BASE}/api/kpis/${selected.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        ...editForm,
        targetValue: Number(editForm.targetValue || 0),
        actualValue: Number(editForm.actualValue || 0),
      }),
    });

    if (!res.ok) {
      alert("수정 실패");
      return;
    }

    setShowDetail(false);
    fetchList(page);
  };

  const handleDelete = async () => {
    if (!selected) return;
    if (!window.confirm("정말 삭제 할까요?")) return;

    const res = await fetch(`${API_BASE}/api/kpis/${selected.id}`, {
      method: "DELETE",
    });

    if (!res.ok) {
      alert("삭제 실패");
      return;
    }

    setShowDetail(false);
    fetchList(page);
  };

  return (
    <>
      <Wrapper>
        <Lnb />
        <DflexColumn>
          <Content>
            <Top />
          </Content>

          <Container fluid className="p-0">
            <Row>
              <Col>
                <Ctap>
                  <SpaceBetween>
                    <h4>KPI관리</h4>
                    <Dflex>
                      <Button className="mx-2 my-3" onClick={handleExcelDownload} variant="success">
                        엑셀다운로드
                      </Button>
                      <Button className="my-3" onClick={() => setShowCreate(true)}>KPI등록</Button>
                    </Dflex>
                  </SpaceBetween>

                  <Table bordered hover>
                    <thead>
                      <tr className="text-center">
                        <th>#</th>
                        {TABLE_HEADERS.map((h) => (
                          <th key={h.key as string}>{h.label}</th>
                        ))}
                      </tr>
                    </thead>
                    <tbody>
                      {rows.map((r, i) => (
                        <tr key={r.id} className="text-center">
                          <td>{i + 1 + page * size}</td>
                          <td
                            style={{ cursor: "pointer", textDecoration: "underline" }}
                            onClick={() => openDetail(r.id)}
                          >
                            {r.kpiName}
                          </td>
                          <td>{r.kpiGroup}</td>
                          <td>{r.owner}</td>
                          <td>{r.periodType}</td>
                          <td>{r.periodValue}</td>
                          <td>{r.targetValue}</td>
                          <td>{r.actualValue}</td>
                          <td>{r.unit}</td>
                          <td>{r.status}</td>
                          <td>{r.useYn}</td>
                          <td>{r.remark}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>

                  <Center>
                    <Pagination>
                      <Pagination.Prev disabled={page === 0} onClick={() => goPage(page - 1)} />
                      {Array.from({ length: totalPages }).map((_, i) => (
                        <Pagination.Item key={i} active={i === page} onClick={() => goPage(i)}>
                          {i + 1}
                        </Pagination.Item>
                      ))}
                      <Pagination.Next disabled={page >= totalPages - 1} onClick={() => goPage(page + 1)} />
                    </Pagination>
                    <PageTotal>
                      총 {totalElements}건 / {page + 1}페이지
                    </PageTotal>
                  </Center>
                </Ctap>
              </Col>
            </Row>
          </Container>
        </DflexColumn>
      </Wrapper>

      {/* 등록 모달 */}
      <Modal show={showCreate} onHide={() => setShowCreate(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>KPI 등록</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Control className="mb-2" name="kpiName" placeholder="KPI명" value={createForm.kpiName} onChange={onCreateChange} />
            <Form.Control className="mb-2" name="kpiGroup" placeholder="그룹" value={createForm.kpiGroup} onChange={onCreateChange} />
            <Form.Control className="mb-2" name="owner" placeholder="담당자" value={createForm.owner} onChange={onCreateChange} />

            <Form.Select className="mb-2" name="periodType" value={createForm.periodType} onChange={onCreateChange}>
              <option value="MONTH">월간</option>
              <option value="QUARTER">분기</option>
              <option value="YEAR">연간</option>
            </Form.Select>

            <Form.Control className="mb-2" name="periodValue" placeholder="기간 (예: 2023-10)" value={createForm.periodValue} onChange={onCreateChange} />

            <Form.Control className="mb-2" type="number" name="targetValue" placeholder="목표값" value={createForm.targetValue} onChange={onCreateChange} />
            <Form.Control className="mb-2" type="number" name="actualValue" placeholder="실적값" value={createForm.actualValue} onChange={onCreateChange} />

            <Form.Control className="mb-2" name="unit" placeholder="단위" value={createForm.unit} onChange={onCreateChange} />

            <Form.Select className="mb-2" name="status" value={createForm.status} onChange={onCreateChange}>
              <option value="ON_TRACK">정상</option>
              <option value="RISK">위험</option>
              <option value="OFF_TRACK">이탈</option>
            </Form.Select>

            <Form.Select className="mb-2" name="useYn" value={createForm.useYn} onChange={onCreateChange}>
              <option value="Y">사용</option>
              <option value="N">미사용</option>
            </Form.Select>

            <Form.Control className="mb-2" name="remark" placeholder="비고" value={createForm.remark} onChange={onCreateChange} />
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowCreate(false)}>
            닫기
          </Button>
          <Button onClick={handleSave}>저장</Button>
        </Modal.Footer>
      </Modal>

      {/* 상세 모달 */}
      <Modal show={showDetail} onHide={() => setShowDetail(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>KPI 상세</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Control className="mb-2" name="kpiName" placeholder="KPI명" value={editForm.kpiName} onChange={onEditChange} />
            <Form.Control className="mb-2" name="kpiGroup" placeholder="그룹" value={editForm.kpiGroup} onChange={onEditChange} />
            <Form.Control className="mb-2" name="owner" placeholder="담당자" value={editForm.owner} onChange={onEditChange} />

            <Form.Select className="mb-2" name="periodType" value={editForm.periodType} onChange={onEditChange}>
              <option value="MONTH">월간</option>
              <option value="QUARTER">분기</option>
              <option value="YEAR">연간</option>
            </Form.Select>

            <Form.Control className="mb-2" name="periodValue" placeholder="기간 (예: 2023-10)" value={editForm.periodValue} onChange={onEditChange} />

            <Form.Control className="mb-2" type="number" name="targetValue" placeholder="목표값" value={editForm.targetValue} onChange={onEditChange} />
            <Form.Control className="mb-2" type="number" name="actualValue" placeholder="실적값" value={editForm.actualValue} onChange={onEditChange} />

            <Form.Control className="mb-2" name="unit" placeholder="단위" value={editForm.unit} onChange={onEditChange} />

            <Form.Select className="mb-2" name="status" value={editForm.status} onChange={onEditChange}>
              <option value="ON_TRACK">정상</option>
              <option value="RISK">위험</option>
              <option value="OFF_TRACK">이탈</option>
            </Form.Select>

            <Form.Select className="mb-2" name="useYn" value={editForm.useYn} onChange={onEditChange}>
              <option value="Y">사용</option>
              <option value="N">미사용</option>
            </Form.Select>

            <Form.Control className="mb-2" name="remark" placeholder="비고" value={editForm.remark} onChange={onEditChange} />
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={handleDelete}>
            삭제
          </Button>
          <Button variant="success" onClick={handleUpdate}>
            수정 저장
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default KpiManagement;
