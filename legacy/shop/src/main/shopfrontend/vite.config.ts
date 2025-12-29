import { defineConfig } from 'vite' //vite설정 객체를 만들때 사용
import react from '@vitejs/plugin-react-swc'
//vite에서 리액트를 제대로 사용하기 위해 불러옴
import path from 'path';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],//위에서 불러온 react플러그인을 실제로 적용한단는 뜻
  resolve:{//경로 별칭을 설정하는 옵션 중복설치 
    alias:{
react: path.resolve('./node_modules/react'),
    },
  },
})
