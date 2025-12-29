import styled from "styled-components";

export const Flex = styled.div`
display:flex;
`;

export const Left = styled.div`
width:10%;
height:100vh;
background-color:#fff;
`;
export const Right = styled.div`
width:90%;
height:100vh;
background-color:#fff;
`;

//캘린더
export const CalendarWrapper = styled.div`
width:100%;
margin:20px auto; 
font-family:sans-serif;
`;
export const CalHeader = styled.h2`
text-align:center; margin-bottom:10px;
`;
export const Grid =styled.div`
display:grid; grid-template-columns:repeat(7, 1fr);
gap:5px;
`;
export const DayName = styled.div`
font-weight:bold; text-align:center;
`;
export const DayCell = styled.div<{isToday?:boolean; isEmpty?:boolean;}>`
height:50px; border-radius:8px; 
background:${({isToday}) => (isToday ? "#ffefc3" : "#f4f4f4")};
display:flex; align-items:center; justify-content:center;
color:${({isEmpty}) => (isEmpty ? "transparent":"#333")};
`;

export const CalTopMargin = styled.div`
margin-top:120px;
`;
