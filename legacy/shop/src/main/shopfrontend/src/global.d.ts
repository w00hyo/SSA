export {};
//이파일을 모듈로 만들어 전역 타입을 올바르게 해준다

declare global{
    interface Window{
        daum:any;
    }
}