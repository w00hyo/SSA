import styled from "styled-components";

export const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding-top: 100px; /* Position it towards the top */
`;

export const ModalContainer = styled.div`
  background-color: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  width: 400px;
  max-width: 90%;
  text-align: center;
  display: flex;
  flex-direction: column;
  gap: 20px;

  /* Simple fade-in animation */
  animation: slideDown 0.3s ease-out;

  @keyframes slideDown {
    from {
      opacity: 0;
      transform: translateY(-20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
`;

export const Message = styled.p`
  font-size: 1.1rem;
  color: #333;
  line-height: 1.5;
  margin: 0;
`;

export const Button = styled.button`
  padding: 10px 20px;
  background-color: #4e73df;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: 600;
  align-self: center;
  min-width: 100px;

  &:hover {
    background-color: #2e59d9;
  }

  &:active {
    transform: scale(0.98);
  }
`;
