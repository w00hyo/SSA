import React from 'react';
import * as S from '../styled/Modal.styles';

interface ModalProps {
  isOpen: boolean;
  message: string;
  onClose: () => void;
}

const Modal: React.FC<ModalProps> = ({ isOpen, message, onClose }) => {
  if (!isOpen) return null;

  return (
    <S.Overlay>
      <S.ModalContainer>
        <S.Message>{message}</S.Message>
        <S.Button onClick={onClose}>OK</S.Button>
      </S.ModalContainer>
    </S.Overlay>
  );
};

export default Modal;
