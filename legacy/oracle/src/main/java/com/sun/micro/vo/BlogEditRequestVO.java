package com.sun.micro.vo;
/*
이제껏 우리는 값을 전달하는 방법으로 Map을 사용했었습니다.
 Map은 굉장히 유연해서 변경에 강하다는 장점이 있습니다만,
 반면 안에 무슨 값이 있는지는 실행하기 전까지 아무도 모른다는
 단점이 있습니다.

반면 VO(Value Object)는 값의 이름과 타입을 미리
정의해서 사용하는 방법입니다. 따라서 코드만 보고도
내용물을 파악할 수 있는 장점이 있지만 코드를 더 많이
써야 하므로 번거롭다는 단점이 있습니다.
추가로 VO는 자동완성이 된다는 최대 장점도 있지요.

프로젝트를 하다가 원리원칙을 중요하게 여기시는 AA분이나 PM분을
만나면 반드시 VO를 사용하라는 지침을 받을 수도 있습니다.
꼭 그렇지 않더라도 매개변수가 엄청나게 많은 입/출력의 경우
미리 정의를 하지 않으면 더 헷깔리는 경우도 있으므로 상황에
따라 적절하게 선택해서 사용하세요.

- PM(Project Manager) : 모든 관리 업무를 책임지는 사람으로 현업,
외부 팀과 인터페이스 및 커뮤니케이션을 담당하고 의견을 조율 하는 담당자
(WBS/휴가/TO관련)

- PL(Project Leader) : PM을 도와 프로젝트의 리딩 담당, 개발,
관리 능력(화면설계, 개발일정관리), 고급기술(DB, WAS등),
커뮤니케이션을 통해 구체적인 요구 사항을 분석하고,
화면을 설계하며 자신이 관리하는 개발자들이 실제 개발을 진행 할 수 있도록
가이드 하고 관리하는 것

- AA (Application Architect) : 공통 로직, 개발표준,
프레임워크 등 공통업무 설계자- TA (Technical Architect)
: OS, WEB, WAS, DB 설치 등 하드웨어와 네트워크 구축 담당자

- DA (Data Architect) : 데이터 표준, 구조, 품질,
마이그레이션 등 DB 설계자- QA (Quality Assurance) :
산출물과 소스코드에 대한 품질을 보증하는 담당자

- BA(business architect) : 기술적 관점이 아닌 비즈니스
관점의 프로세스 설계자

* */
public class BlogEditRequestVO {

    public int getBlogContSeq() {
        return blogContSeq;
    }

    public void setBlogContSeq(int blogContSeq) {
        this.blogContSeq = blogContSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContBdy() {
        return contBdy;
    }

    public void setContBdy(String contBdy) {
        this.contBdy = contBdy;
    }

    private int blogContSeq;
    private String title;
    private String contBdy;



}
