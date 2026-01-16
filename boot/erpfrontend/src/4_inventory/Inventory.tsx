import {Container, Row, Col, Table, Button, Modal, Tab, Tabs,
Form,
} from "react-bootstrap";
import Top from "../include/Top";
import Header from "../include/Header";
import SideBar from "../include/SideBar";
import {Left, Right, Flex, TopWrap, RoundRect} from "../stylesjs/Content.styles";
import {useMemo, useState} from "react";
import { JustifyContent } from "../stylesjs/Util.styles";
import { TableTitle, TabTitle } from "../stylesjs/Text.styles";
import { InputGroup, Search, Select, Radio, Label } from "../stylesjs/Input.styles";
import { WhiteBtn, MainSubmitBtn, BtnGroup } from "../stylesjs/Button.styles";

type SortDirection = "asc" | "desc";

type SortState = {key: string | null; direction:SortDirection;}





type ColumnDef = {
    key:string; //데이터키 (unique)
    label:string; //화면에 보이는 헤더명
}

const initialColumns : ColumnDef[] = [
  { key: "itemCode", label: "품목코드" },
  { key: "itemName", label: "품목명" },
  { key: "itemGroup", label: "품목그룹" },
  { key: "spec", label: "규격" },
  { key: "barcode", label: "바코드" },
  { key: "inPrice", label: "입고단가" },
  { key: "outPrice", label: "출고단가" },
  { key: "itemType", label: "품목구분" },
  { key: "image", label: "이미지" },
];

const Inventory = () => {
//모달관련
const [show, setShow] = useState(false);
const handleClose = () => setShow(false);
const handleShow = () => setShow(true);


    const [x, setX] = useState(0);
    //헤더 / 푸터 컬럼은 json(상태)로 관리
    const [columns, setColumns] = useState<ColumnDef[]>(initialColumns);

    const [sort, setSort] = useState<SortState>({//다른곳에 잘못선언하면 백지
key: null, direction:"asc",
});

    //사용자가 자유롭게 추가 할 컬럼을 입력
    const[newColLabel, setNewColLabel] = useState("");
    const[newColkey, setNewColKey] = useState("");

    const removeColumn = (key: string) => {

    }

    //정렬 토클함수 만들기
const toggleSort = (key: string) => {
    setSort((prev:any) => {
        //같은 컬럼 클릭 -> 방향만 토글
        if(prev.key === key) {
            return{
                key,
                direction:prev.direction === "asc" ? "desc" : "asc",
            };
        }

        //다른 컬럼 클릭 ->asc부터 시작
        return{
            key,
            direction:"asc",
        }
    })
}
    return(
<>
<div className="fixed-top">
  <Top/>
  <Header/>
</div>
<SideBar/>
<Container fluid>
    <Row>
        <Col>
        <Flex>
            <Left>

            </Left>
            <Right>
<TopWrap/>{/*헤더를 사용하는 만큼에 높이만큼 설정을 해야 보임 */}
<JustifyContent>
    
    <TableTitle>
        품목등록리스트
    </TableTitle> 

<InputGroup>

<WhiteBtn className="mx-2">
사용중단포함
</WhiteBtn>

<Search type="search" placeholder="검색"/>
<MainSubmitBtn className="mx-2">
Search(F3)
</MainSubmitBtn>

<Select className="mx-2">
<option>품목계정추가</option>
<option>다공정품목설정</option>
<option>다규격품목설정</option>
<option>양식설정</option>
<option>조건양식설정</option>
<option>검색항목설정</option>
<option>기능설정</option>
</Select>
</InputGroup>
    

</JustifyContent>
<Table responsive>
    <thead>
        <tr>
{columns.map((c) => {
    const isActive = sort.key === c.key;
    const dir = sort.direction;
    
    return(
<th key={c.key}>
<div className="">
    <span>{c.label}</span>
    <Button
    size="sm" variant="light"
    onClick={()=> toggleSort(c.key) } className="mx-2">
        {!isActive && "정렬"}
        {isActive && dir === "asc" && "▲"}
        {isActive && dir === "desc" && "▼"}
    </Button>
</div>
</th>);
})}
        </tr>
    </thead>
    <tbody>
        <tr>
            <td></td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <th></th>
        </tr>
    </tfoot>
</Table> 
<BtnGroup>
    <MainSubmitBtn onClick={handleShow}>
        신규(F2)
    </MainSubmitBtn>
</BtnGroup>          
            </Right>
        </Flex>
        </Col>
    </Row>
</Container>

<Modal show={show} onHide={handleClose} >
    <Modal.Header closeButton>
        <Modal.Title></Modal.Title>
    </Modal.Header>
    <Modal.Body>
<TabTitle>품목등록</TabTitle>
<Tabs
defaultActiveKey="basic"
justify
>
    <Tab eventKey="basic" title="기본">
<RoundRect>

    <InputGroup>
        <label className="w-25">품목코드</label>
        <Form.Control type="text" placeholder="예시 Z00021" className="w-75"/>
    </InputGroup>

    <InputGroup className="my-3">
        <label className="w-25">품목명</label>
        <Form.Control type="text" placeholder="품목명" className="w-75"/>
    </InputGroup>

    <InputGroup>
        <label className="w-25">규격</label>
    <div >
        <Flex className="my-2">
        <Radio type="radio"/><Label className="mx-2">규격명</Label>
        </Flex>
        <Form.Control type="text" placeholder="단위" className="w-75"/>
    </div>
    </InputGroup>

    <InputGroup>
        <label className="w-25">단위</label>
        <Form.Control type="text" placeholder="단위" className="w-75"/>
    </InputGroup>

    <InputGroup className="my-3">
        <label className="w-25">생산공정</label>
        <Form.Control type="text" placeholder="생산공정" className="w-75"/>
    </InputGroup>

    <InputGroup>
        <label className="w-25">입고단가</label>
        <Form.Control type="text" placeholder="입고단가" className="w-75"/>
    </InputGroup>

    <InputGroup className="my-3">
        <label className="w-25">출고단가</label>
        <Form.Control type="text" placeholder="입고단가" className="w-75"/>
    </InputGroup>

</RoundRect>
    </Tab>
    <Tab eventKey="" title="품목정보">
        
    </Tab>
    <Tab eventKey="" title="수량">
        
    </Tab>
    <Tab eventKey="" title="단가">
        
    </Tab>
    <Tab eventKey="" title="원가">
        
    </Tab>
    <Tab eventKey="" title="부가정보">
        
    </Tab>
    <Tab eventKey="" title="관리대상">
        
    </Tab>
</Tabs>
    </Modal.Body>
    <Modal.Footer>
<Button variant="secondary" onClick={handleClose}>Close</Button>
<Button variant="primary" onClick={handleClose}>Save Change</Button>
    </Modal.Footer>
</Modal>
</>
    );
}
export default Inventory;