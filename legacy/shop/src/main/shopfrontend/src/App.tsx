import { BrowserRouter, Routes, Route } from "react-router-dom"
import { AuthProvider } from "./AuthContext"
import Header from "./include/Header"
import Home from "./Home"
import Member from "./sub/Member"
import Login from "./sub/Login"
import 'bootstrap/dist/css/bootstrap.min.css';
import "./assets/scss/style.scss";


function App() {


  return (
    <>
<AuthProvider>
<BrowserRouter>
<Header/>
<Routes>
<Route path="/" element={<Home/>}/>
<Route path="/member" element={<Member/>}/>
<Route path="/login" element={<Login/>}/>
</Routes>
</BrowserRouter>    
</AuthProvider>
    </>
  )
}

export default App
