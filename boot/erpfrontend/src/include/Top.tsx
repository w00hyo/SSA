import styled from "styled-components";
import {Container, Row, Col} from "react-bootstrap";

const HeaderWrap = styled.div`
background-color:white; padding:1rem;
@media (max-width:768px) {display:none;}
`;

const Link = styled.a`
margin-right:1rem;
text-decoration:none;
color:black;
border-bottom :2px solid transparent;
transition:all 0.5s;

&:hover{
border-bottom-color:hotpink;
color:hotpink;
cursor:pointer;
}
`;

const Top = () => {
    return(
        <>
<HeaderWrap>
<Container fluid>
    <Row>
        <Col md={12}>
<Link to="">자금 현황표</Link>
<Link to="">지급어음조회</Link>
<Link to="">견적서입력</Link>
<Link to="">구매조회</Link>
<Link to="">손익계산서</Link>
<Link to="">판매입력2</Link>
<Link to="">재고변동표</Link>
<Link to="">판매입력</Link>
<Link to="">재고현황</Link>
<Link to="">일반전표</Link>
<Link to="">판매조회</Link>
<Link to="">거래처리스트</Link>
        </Col>
    </Row>
</Container>
</HeaderWrap>
        </>
    )
}
export default Top;