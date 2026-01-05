import styled from "styled-components";


export const Fixed = styled.div`
background:rgba(0,0,0,.8);
width:100%; height:100%; 
display:flex; align-items:center;
left:0; top:0; z-index:999999;
justify-content:center;
position:absolute;
`;

export const Modal =styled.div`
width: 440px;
background:#fff;
border-radius: 12px;
padding:16px;
`;


export const ModalTitle = styled.h1`
`;
export const ModalDate = styled.h5`
`;