import { createContext, useContext, useState,type ReactNode } from "react";


type User = {//로그인한 유저 정보를 이런 형태로 쓰겠다고 타입을 먼저 정의한 거예요.
    firstName : string;
    lastName : string;
    email: string;
};

type AuthContextType = {//이건 AuthContext 안에 무엇을 넣을지에 대한 타입입니다.
    user: User | null;
    //user: 현재 로그인한 사용자 정보 (User 타입) 또는 null (로그인 안 한 상태)
    login: (user: User) => void;
    //login: User 객체를 받아서 로그인 처리하는 함수
    logout: () => void;
    //logout: 아무 인자도 받지 않고 로그아웃 처리하는 함수
}

const AuthContext = createContext<AuthContextType | undefined> (undefined);
/*
createContext 전역상태저장소를 하난 만든것이고 
여기엔 AuthContextType라는 형태의 값이 들어갑니다
제네릭 AuthContextType | undefined
기본값을 undefined 주었기때문에 타입도 AuthContextType 또는 정의되지 않음이 될수 있다고 명시
왜 정의되지 않음을 하냐면
나중에 useAuth()훅에서 Provider 바깥에서 잘못 사용했는지 검사하기 위해서 이다
만약 기본값을 {user: null,....}이런걸로 넣어 버리면 
provider밖에서 써도 에러없이 동작해 버려서 문제를 찾기 힘듬
*/

export const AuthProvider = ({children}:{children: ReactNode}) => {
    //이컴포넌트는   Context의 값(로그인 상태)을 실제로 제공하는 Provider에요
    const [user, setUser] =useState<User | null>(() => {
        //유즈 스테이트에 함수를 넘감 Lazy initializer 처음 렌더링할때 딱한번만 이함수를 실행해요

        const saved = localStorage.getItem("loginUser");
        //이전에 로그인할때 저장해 둔 문자열이 있으면 가져옴
        return saved ? JSON.parse(saved) : null;
        //save가 존재하면 JSON.parse(saved) 해서 문자열 객체로 변환 
        //없으면 null을 리턴->로그인 정보 없음
        //결과 새로고침해도 localStorage 에 저장된 값으로 로그인 상태를 복원
    });

    const login = (u:User) => {
    /*
    매개변수 : 백앤드에서 받아온 사용자 정보 등 User타입 객체
    setUser(u); 상태에서 user객체 저장 -> 화면이 로그인된 상태로 다시 렌더링
    localStorage.setItem("loginUser",JSON.stringify(u));
    - 브라우저의 로컬 스토리지에 문자열로 저장
    - 나중에 새로고침 했을때 JSON.parse로 다시 복원
    */
        setUser(u);
        localStorage.setItem("loginUser",JSON.stringify(u));
    }

    const logout = () => {
        setUser(null);
        localStorage.removeItem("loginUser");
    }
    /*
    현재 로그인 상태를 날리고 
    로그인 안한 상태로 변경
    브라우저 저장소에서도 기록 삭제
    */
   return(
    <AuthContext.Provider value={{user, login, logout}}>
        {children}
    </AuthContext.Provider>
    /*이렇게 하면 이 Provider로 감싸진 모든 하위 컴포넌트에서 useAuth()로 user, login, logout에 접근가능 */
   );
};
export const useAuth = ()  => {
    const ctx = useContext(AuthContext);
    if(!ctx){
        throw new Error("useAuth는 AuthProvider안에서만 사용해야 합니다");
    }
    return ctx;
}