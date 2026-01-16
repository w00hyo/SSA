import {Container, Row, Col, Table} from "react-bootstrap";
import Top from "../include/Top";
import Header from "../include/Header";
import SideBar from "../include/SideBar";
import {Left, Right, Flex} from "../stylesjs/Content.styles";
import {useMemo, useState} from "react";

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

    const [columns, setColumns] = useState<ColumnDef[]>(initialColumns);

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
<Table responsive>
    <thead>
        <tr>
            <th></th>
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
            </Right>
        </Flex>
        </Col>
    </Row>
</Container>
</>
    );
}
export default Inventory;