import styled from "styled-components";
/*
npm install styled-components
*/
const SidebarWrapper = styled.div`
width:80px;
height:100%;
display:flex;
flex-direction:column;
position:fixed;
right:0; top:0;
justify-content:flex-end;
align-items:center;
z-index:999999;
background-color:hotpink;

@media (max-width:768px) {
display:none;
}
`;//position:fixed 는 언제든 포지션 고정 

const Icon = styled.img`
width:32px; height:32px;
position:relative;
margin-top:10px;
`;

const SideBar = () => {
    return(
        <>
        <SidebarWrapper>
        <Icon src="./img/ic1.png" alt="icon"/>
        <Icon src="./img/ic2.png" alt="icon"/>
        <Icon src="./img/ic3.png" alt="icon"/>
        <Icon src="./img/ic4.png" alt="icon"/>
        <Icon src="./img/ic5.png" alt="icon"/>
        <Icon src="./img/ic6.png" alt="icon"/>
        <Icon src="./img/ic7.png" alt="icon"/>
        <Icon src="./img/ic8.png" alt="icon"/>
        <Icon src="./img/ic9.png" alt="icon"/>
        <Icon src="./img/ic10.png" alt="icon"/>
        <Icon src="./img/ic11.png" alt="icon"/>
        <Icon src="./img/ic12.png" alt="icon"/> 
        <Icon src="./img/ic13.png" alt="icon"/>
        <Icon src="./img/ic14.png" alt="icon"/> 
        <Icon src="./img/ic15.png" alt="icon"/> 
        <Icon src="./img/ic16.png" alt="icon"/>   
        </SidebarWrapper>
        </>
    )
}
export default SideBar;