import { useState } from "react";
import axios from "axios";
import { Container, Row, Col, Button, Form, Card } from "react-bootstrap";

const Member = () => {

  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    repeatPassword: "",
    gender: "",
    companyName: "",
    position: "",
    tel: "",
    address: "",
    detailAddress: ""
  });

  const onChange = (e:any) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Daum 주소검색
  const searchAddress = () => {
    new window.daum.Postcode({
      oncomplete: function (data:any) {
        setForm((prev) => ({
          ...prev,
          address: data.address
        }));
      }
    }).open();
  };

  // 제출
  const onSubmit = async (e:any) => {
    e.preventDefault();

    if (form.password !== form.repeatPassword) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    const res = await axios.post("http://localhost:8181/api/member/register", form);

    if (res.data === "success") {
      alert("회원가입 완료!");
      window.location.href = "/login";
    } else {
      alert("회원가입 실패");
    }
  };

  return (
    <Container className="mt-5">
      <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" async></script>

      <Card className="o-hidden border-0 shadow-lg mt-150">
        <Card.Body className="p-0">
          <Row>
            <div className="col-lg-5 d-none d-lg-block bg-register-image"></div>
            <div className="col-lg-7">
              <div className="p-5">

                <Form className="user" onSubmit={onSubmit}>

                  <div className="form-group row mb-2">
                    <Col sm={6}>
                      <Form.Control
                        type="text"
                        placeholder="이름"
                        name="firstName"
                        value={form.firstName}
                        onChange={onChange}
                        className="form-control-user"
                      />
                    </Col>

                    <Col sm={6}>
                      <Form.Control
                        type="text"
                        placeholder="성"
                        name="lastName"
                        value={form.lastName}
                        onChange={onChange}
                        className="form-control-user"
                      />
                    </Col>
                  </div>

                  <Form.Control
                    type="email"
                    placeholder="이메일"
                    name="email"
                    className="mb-2 form-control-user"
                    value={form.email}
                    onChange={onChange}

                  />

                  <div className="form-group row mb-2">
                    <Col sm={6}>
                      <Form.Control
                        type="password"
                        placeholder="비밀번호"
                        name="password"
                        value={form.password}
                        onChange={onChange}
                        className="form-control-user"
                      />
                    </Col>

                    <Col sm={6}>
                      <Form.Control
                        type="password"
                        placeholder="비밀번호 확인"
                        name="repeatPassword"
                        value={form.repeatPassword}
                        onChange={onChange}
                        className="form-control-user"
                      />
                    </Col>
                  </div>

                  <div className="form-group mb-2">
                    <label className="mr-3">성별 : </label>
                    <Form.Check inline type="radio" label="남성" name="gender" value="male" onChange={onChange}/>
                    <Form.Check inline type="radio" label="여성" name="gender" value="female" onChange={onChange}/>
                    <Form.Check inline type="radio" label="기타" name="gender" value="other" onChange={onChange}/>
                  </div>

                  <div className="d-flex mb-2">
                    <Form.Control
                      type="text"
                      placeholder="회사명"
                      name="companyName"
                      value={form.companyName}
                      onChange={onChange}
                      className="form-control-user"
                    />
                    <Form.Control
                      type="text"
                      placeholder="직급"
                      className="mx-4 form-control-user"
                      name="position"
                      value={form.position}
                      onChange={onChange}
                    />
                    <Form.Control
                      type="text"
                      placeholder="전화번호 인척하면서 휴대폰번호 수집"
                      name="tel"
                      value={form.tel}
                      onChange={onChange}
                      className="form-control-user"
                    />
                  </div>

                  <div className="d-flex mb-2">
                    <Form.Control
                      type="text"
                      name="address"
                      placeholder="주소"
                      value={form.address}
                      className="form-control-user"
                      readOnly
                    />
                    <Button className="w-25 ms-2" onClick={searchAddress} type="button">주소검색</Button>
                  </div>

                  <Form.Control
                    type="text"
                    placeholder="상세주소"
                    name="detailAddress"
                    className="mb-3 form-control-user"
                    value={form.detailAddress}
                    onChange={onChange}
                  />

                  <Button type="submit" variant="primary" className="btn-user btn-block mb-2">
                    Register Account
                  </Button>

                </Form>

              </div>
            </div>
          </Row>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default Member;
