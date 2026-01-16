import {Container, Row, Col, Table, Button, Modal, Tab, Tabs,
Form,
} from "react-bootstrap";
import Top from "../include/Top";
import Header from "../include/Header";
import SideBar from "../include/SideBar";
import {Left, Right, Flex, TopWrap, RoundRect} from "../stylesjs/Content.styles";
import {useMemo, useState} from "react";
import { JustifyContent, W70, W30, W80, W20 } from "../stylesjs/Util.styles";
import { TableTitle, TabTitle } from "../stylesjs/Text.styles";
import { InputGroup, Search, Select, Radio, Label, MidLabel, CheckGroup, Check } from "../stylesjs/Input.styles";
import { WhiteBtn, MainSubmitBtn, BtnGroup, SmallBadge } from "../stylesjs/Button.styles";

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

<Modal show={show} onHide={handleClose} 
size="lg"
centerd
>
    <Modal.Header closeButton>
        <Modal.Title>품목등록</Modal.Title>
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
    <W30>
        <MidLabel>품목코드</MidLabel>
        </W30>
        <W70>
        <Form.Control type="text" placeholder="예시 Z00021" />
        </W70>
    </InputGroup>

    <InputGroup className="my-3">
    <W30>
        <MidLabel>품목명</MidLabel>
        </W30>
        <W70>
        <Form.Control type="text" placeholder="품목명" />
        </W70>
    </InputGroup>

<Flex>
    <W30>
  <MidLabel className="">규격</MidLabel>
  </W30>
  <W70>
  <div className="">
        <Flex className="my-2">
            <Radio type="radio"/><Label className="mx-2">규격명</Label>
            <Radio type="radio"/><Label className="mx-2">규격그룹</Label>
            <Radio type="radio"/><Label className="mx-2">규격계산</Label>
            <Radio type="radio"/><Label className="mx-2">규격계산그룹</Label>
        </Flex>
        <Form.Control type="text" placeholder="단위"/>
   </div>
   </W70>
</Flex>
   

    <InputGroup className="my-3">
    <W30>
        <MidLabel>단위</MidLabel>
        </W30>
        <W70>
        <Form.Control type="text" placeholder="단위" className="w-75"/>
        </W70>
    </InputGroup>

<Flex>
  <W30>
  <MidLabel className="">품목구분</MidLabel>
  </W30>
  <W70>
        <Flex className="my-2">
            <Radio type="radio"/><Label className="mx-2">원재료</Label>
            <Radio type="radio"/><Label className="mx-2">부재료</Label>
            <Radio type="radio"/><Label className="mx-2">제품</Label>
            <Radio type="radio"/><Label className="mx-2">반제품</Label>
            <Radio type="radio"/><Label className="mx-2">상품</Label>
            <Radio type="radio"/><Label className="mx-2">무형상품</Label>
        </Flex>
            <Flex className="my-2">
            <SmallBadge className="mx-5">세트여부</SmallBadge>
            <Radio type="radio"/><Label className="mx-2">사용</Label>
            <Radio type="radio"/><Label className="mx-2">사용안함</Label>
        </Flex>     
   </W70>
</Flex>

    <InputGroup className="my-3">
        <W30>
            <MidLabel>생산공정</MidLabel>
        </W30>
        <W70>
            <Form.Control type="text" placeholder="생산공정" />
        </W70>
    </InputGroup>

    <InputGroup>
        <W30>
            <MidLabel>입고단가</MidLabel>
        </W30>
        <W70>
            <Flex>
                <Form.Control type="text" placeholder="입고단가" />
                <CheckGroup>
                    <Check type="checkbox" className="mx-2"/>
                    <Label>VAT 포함</Label>
                </CheckGroup>
            </Flex>
        </W70>
    </InputGroup>

    <InputGroup className="my-3">
        <W30>
            <MidLabel>출고단가</MidLabel>
        </W30>
        <W70>
            <Flex>
                <Form.Control type="text" placeholder="입고단가"/>            
                <CheckGroup>
                    <Check type="checkbox"  className="mx-2"/>
                    <Label>VAT 포함</Label>
                </CheckGroup>
            </Flex>
        </W70>
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