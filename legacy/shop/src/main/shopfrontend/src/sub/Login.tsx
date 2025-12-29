import {useState} from "react";
//이메일 / 비밀번호를 관리하기 위한 react hook 
// hook ? 후크선장 : 생명주기[] 기능을 연동할수 있게 해주는 함수
import axios from "axios";
//서버에 HTTP요청 POST / GET 등을 보내기 위한 라이브러리
import {useNavigate} from "react-router-dom";
//로그인 성공후 페이지 이동
import {Container,Row, Col, Card, Form, Button} from "react-bootstrap";
import {useAuth} from "../AuthContext";
//아까 만들었던 후크 hook

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const {login} = useAuth();
    const navigate = useNavigate();

    const onSubmit = async (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();//고유에 성격 중지

        try{
            const res = await axios.post("http://localhost:8181/api/member/login",{
                email, password,
            });
            const data = res.data;//1)
            if(data.result === "success"){//만약 로그인이 성공한다면
                //전역상태 저장
                login({
                firstName:data.firstName,
                lastName:data.lastName,
                email:data.email,
                });
                alert("로그인 성공;");
                navigate("/");
            }else{
                alert(data.message || "로그인 실패");
            }
        }catch(err){
            console.error(err);
            alert("로그인중 오류가 발생 했습니다");
        }
    };

    return(
        <>
         <Container>

        {/*Outer Row*/}
        <Row className="justify-content-center">

            <Col xl={10} lg={12} md={9}>

                <Card className="o-hidden border-0 shadow-lg my-5">
                    <Card.Body className="p-0">
                        {/*Nested Row within Card Body*/}
                        <Row>
                            <Col lg={6} className="d-none d-lg-block bg-login-image"></Col>
                            <div className="col-lg-6">
                                <div className="p-5">
                                    <div className="text-center">
                                        <h1 className="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                    </div>
                                    <Form className="user" onSubmit={onSubmit}>
                                        <Form.Group className="mb-3">
                                            <Form.Control 
                                            type="email" 
                                            className="form-control-user"
                                            placeholder="이메일을 입력하세요"
                                            onChange={(e) => setEmail(e.target.value)}
                                            />
                                        </Form.Group>
                                        <Form.Group className="mb-3">
                                            <Form.Control 
                                            type="password" 
                                            className="form-control-user"
                                            placeholder="Password"
                                            value={password}
                                            onChange={(e) => setPassword(e.target.value)}
                                            />
                                        </Form.Group>
                                        <div className="form-group">
                                            <div className="custom-control custom-checkbox small">
                                                <input type="checkbox" className="custom-control-input" id="customCheck"/>
                                                <label className="custom-control-label" htmlFor="customCheck">Remember
                                                    Me</label>
                                            </div>
                                        </div>
                                        <Button 
                                        type="submit" 
                                        variant="primary"
                                        className="btn-user btn-block"
                                        >
                                            Login
                                        </Button>
                                        <hr/>
                                        <a href="index.html" className="btn btn-google btn-user btn-block">
                                            <i className="fab fa-google fa-fw"></i> Login with Google
                                        </a>
                                        <a href="index.html" className="btn btn-facebook btn-user btn-block">
                                            <i className="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                        </a>
                                    </Form>
                                    <hr/>
                                    <div className="text-center">
                                        <a className="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>
                                    <div className="text-center">
                                        <a className="small" href="register.html">Create an Account!</a>
                                    </div>
                                </div>
                            </div>
                        </Row>
                    </Card.Body>
                </Card>

            </Col>

        </Row>

    </Container>

        </>
    )
}
export default Login;