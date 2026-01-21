import { useEffect, useState } from "react";
import Lnb from "../include/Lnb";
import Top from "../include/Top";
import { Wrapper, DflexColumn, DflexColumn2, Content, Ctap } from "../styled/Sales.styles";
import { Container, Row, Col, Tab, Tabs, Table, Button, Modal, Form, Pagination } 
from "react-bootstrap";

const API_BASE = "http://localhost:9500"; //기본url을 변경이나 간략히 사용하기 위해서

type ProductionOrder = {//생산지시서 한 건의 정보 구조”**를 정의
orderDate: string; workOrderNo:string; itemCode:string; itemName:string; planQty:number;
startDate:string; endDate:string; status:string;
}

type PageResponse<T> = {//목록 데이터를 페이지 단위로 받을 때 쓰는 공통 형식
//<T> 아무 타입이나 들어올 수 있는 자리
content:T[]; totalElements:number; totalPages:number;
number:number; size:number;
}

const ProductionManagement = () => {

    const[rows, setRows] = useState<ProductionOrder[]>([]);
//서버에서 받아온 생산지시 데이터를 화면에 뿌리기 위해
    const[page, setPage] = useState(0);
//페이지 이동(다음 / 이전)을 하기 위해
    const[size] = useState(10);
//한 페이지에 보여줄 개수 서버에 “10개씩 주세요”라고 요청하기 위해
    const[totalPages, setTotalPages] =useState(0);
//마지막 페이지인지 판단 페이지 버튼(1, 2, 3 …) 만들 때 필요
    const [showCreate, setShowCreate] = useState(false);
//등록 화면(모달/폼)을 보여줄지 말지
    const [form, setForm] = useState({
orderDate:"", itemCode:"", itemName:"", planQty:"", startDate:"", endDate:"",        
    })
//사용자가 입력 중인 생산지시 데이터 입력값을 저장,입력 중에도 값 유지, 저장 버튼 클릭 시 서버로 전송 

const handleChange = (e:React.ChangeEvent<any>) => {
/*input 값이 바뀔 때 실행되는 함수
e는 “무슨 입력창이, 어떤 값으로 바뀌었는지”에 대한 정보예요.
👉 form 상태 업데이트
이게 무슨 뜻이냐면:
1️⃣ prev
→ 기존에 입력되어 있던 form 값
2️⃣ { ...prev }
→ 기존 값은 그대로 복사
3️⃣ [name]: value
→ 바뀐 입력값만 덮어쓰기
*/
const {name, value} = e.target;
setForm((prev) => ({...prev, [name]:value }));
}
    return(
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
              <Ctap></Ctap>
              </Col>
              </Row>
              </Container>
              </DflexColumn>
              </Wrapper>
</>
    )
}

export default ProductionManagement;