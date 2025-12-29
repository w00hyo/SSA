import {Button, Container, Form, Nav, 
  Navbar, NavDropdown, Offcanvas} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import {useAuth} from '../AuthContext';

const Header = () => {

  const {user, logout} = useAuth();

    return(
        <>{/*React Fragment : 여러개의 컴포넌트를 하나의 부모요소로 묶기 위해 사용 */}
{['sm'].map((expand) => (

<Navbar key={expand} 
expand={expand} 
className="bg-body-tertiary mb-3 fixed-top">
{/* 
React-Bootstrap의 <Navbar> 컴포넌트에서 expand는 반응형(Responsive) 동작을 제어하는 핵심 속성이에요.
key={expand} : React에서 map 반복 시 고유 key 필요
expand={expand} : 각 반복에 맞는 반응형 설정
*/}
  <Container fluid>
<Navbar.Brand href="/">samSHOP</Navbar.Brand>
  <Navbar.Toggle aria-controls={`offcanvasNavbarLabel-${expand}`}/>
  {/* aria-controls는 어떤 Offcanvas를 제어할지 식별 (접근성용)*/}
    <Navbar.Offcanvas
    id={`offcanvasNavbar-expand-${expand}`}
    aria-labelledby={`offcanvasNavbar-expand-${expand}`}
    placement="end"
    >{/* id={offcanvasNavbar-expand-${expand}}오프캔버스의 고유한 HTML id 속성을 설정합니다.
백틱(`)과 ${expand}는 템플릿 문자열 문법으로, 변수 expand 값이 문자열에 들어갑니다.aria-labelledby={offcanvasNavbarLabel-expand-${expand}}
접근성(Accessibility, a11y)을 위한 속성입니다.
스크린 리더(시각장애인용 리더기)가 이 오프캔버스의 제목(label)을 어디서 읽어와야 하는지를 알려줍니다.*/}
      
      <Offcanvas.Header closeButton>
        <Offcanvas.Title id={`offcanvasNavbar-expand-${expand}`}>
          samERP Menu
        </Offcanvas.Title>
      </Offcanvas.Header>

      <Offcanvas.Body>
        <Nav className='justify-content-end flex-grow-1 pe-3'>
          {/*링크들을 오른쪽 끝으로 정렬 오른쪽 패딩에 공백 */}
              <Nav.Link as={Link} to="/">Home</Nav.Link>
              <Nav.Link as={Link} to="/member">회원가입</Nav.Link>
              <Nav.Link as={Link} to="/about">about</Nav.Link>
              <Nav.Link as={Link} to="/contact">contact</Nav.Link>
              {/*버튼에 2단 2depth는 드롭다운 */}
              <NavDropdown title="고객정보"id={`offcanvasNavbar-expand-${expand}`}
              >
<NavDropdown.Item as={Link} to="/">작성자</NavDropdown.Item>
              </NavDropdown>
{!user && (
  <Nav.Link as={Link} to="/login">
    로그인
  </Nav.Link>
)}              
</Nav> 
        <Form className='d-flex'>
          <Form.Control
          type="search"
          placeholder='search'
          className='me-2'
          aria-label='Search'
          />
          <Button variant="outline-success">Search</Button>
        </Form>

{user &&(
  <div className="d-flex align-items-center">
    <span className='me-2'>
    {user.firstName} {user.lastName} 님 환영합니다  
    </span>
    <Button
    variant='outline-secondary'
    size="sm"
    onClick={logout}
    >
    로그아웃
    </Button>
  </div>
)}
      </Offcanvas.Body>
    
    </Navbar.Offcanvas>
  </Container>
</Navbar>


))}
        </>
    )
}
/*
  <nav>
    <Link to="/">Home</Link>|{" "}
    <Link to="/about">about</Link>|{" "}
    <Link to="/contact">contact</Link>
  </nav>
*/
export default Header;
/*
<NavDropdown.Item as={Link} to="/">작성자</NavDropdown.Item>
<NavDropdown.Item as={Link} to="/">고객명</NavDropdown.Item>
<NavDropdown.Divider/>
<NavDropdown.Item as={Link} to="/">자택</NavDropdown.Item>
<NavDropdown.Item as={Link} to="/">접수경로</NavDropdown.Item>
*/