package com.sm.kr.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sm.kr.dto.BootCreateDTO;
import com.sm.kr.dto.BootEditDTO;
import com.sm.kr.dto.BootEditResponseDTO;
import com.sm.kr.dto.BootListResponseDTO;
import com.sm.kr.dto.BootReadResponseDTO;
import com.sm.kr.service.BootService;

@Controller
public class AutoController {
	
	@Autowired
	private BootService bootService;
	
	@GetMapping("lunch/create")//url 리턴
	public String create() {
		return "lunch/create";
	}
	
	@PostMapping("lunch/create")
	public String insert(BootCreateDTO bootCreateDTO) {
		Integer bootId= this.bootService.insert(bootCreateDTO);
		return String.format("redirect:/lunch/read/%s", bootId);
	}
	
	/*기존에 있던 리턴시키는것 url */
	@GetMapping("lunch/read/{bootId}")
	public ModelAndView read(@PathVariable Integer bootId) {
	ModelAndView mav = new ModelAndView();
	
	try {//정상적인 로직을 실행
		BootReadResponseDTO bootReadResponseDTO = this.bootService.read(bootId);
		//모델에 "bootReadResponseDTO" 라는 name로  bootReadResponseDTO라는 객체를 추가 
		mav.addObject("bootReadResponseDTO",bootReadResponseDTO);
		mav.setViewName("lunch/read");
	}catch(NoSuchElementException ex) {
		
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		//bootId형식적으론 맞지만 존재하지 않는 리소스 422
		mav.addObject("message", "선택하신 글이 없습니다");
		mav.addObject("location", "/lunch");
		mav.setViewName("common/error/422");		
	}
	/*
	@PathVariable Integer bootId 
	url 경로에 전달된 bootId값을 Integer 타입으로 받아옵니다
	Model(brain 데이터베이스 작동 로직 backend) 
	View(화면에 그려지는 걸 frontend)
	*/
	
	return mav;
	}
	
	/*//위에처럼 오류가 발생 했을때 마다 try catch로 사용하면 쓰는 부분이 불편하니 
	//그렇게 사용하지 않고 예외가 발생하면 실행되는 메소드를 정의해 봅니다
	@ExceptionHandler(NoSuchElementException.class)
	//스프링 mvc에서 컨트롤러 혹은 전역예외처리(@ControllerAdvice)에서 사용됨
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
		//뷰(프론트[앞단{ui/ux}]) 와 백단(backend{model})을 사용해야 할때 모델앤드뷰를 사용함 
		ModelAndView mav = new ModelAndView();
		//상태 요청은 잘 되었지만 서버가 처리할수 없는 내용임 존재하지 않는 순번에 글을 수정할때		
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
//뷰에 전달할 데이터 모델을 추가 템플릿에서 ${message}로 접근가능, 사용자에게 보여질 에러메세지
		mav.addObject("message", "선택하신글에 정보가 없습니다");
		mav.addObject("location", "/lunch/list");
//에러후 리디렉션을 유도할수 있는 경로 에러페이지 노출후 다시 뒤로가는 경로
		mav.setViewName("common/error/422");
		//에러가 생겼을때 보여주는 url
		//url http://www.sksk.ssm/query=1
		return mav;
	}*/
	
	@GetMapping("/lunch/edit/{bootId}")//읽기 서비스 로직 실행 HTTP GET 요청을 처리
	public ModelAndView edit(@PathVariable Integer bootId) throws NoSuchElementException{
		//@PathVariable Integer bootId : url경로에 있는 {bootId}값을 Integer타입으로 받아옵니다
		//throws NoSuchElementException 해당id가 없을때 에외를 던집니다
		//ModelAndView 는 모델(데이터)과 뷰(화면이름)를 함께 리턴하는 스프링 객체
		ModelAndView mav = new ModelAndView();
		
		BootEditResponseDTO bootEditResponseDTO = this.bootService.edit(bootId);
		mav.addObject("bootEditResponseDTO",bootEditResponseDTO);
		mav.setViewName("lunch/edit");		
		return mav;
		//수정해야되는 글 클릭했을때 보이기 까지
	}
	
	//수정이 잘못되거나 실패할때 돌리는것
	@ExceptionHandler(NoSuchElementException.class)
	//스프링 mvc에서 컨트롤러 혹은 전역예외처리(@ControllerAdvice)에서 사용됨
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
	    return this.error422("수정할 정보가 없습니다","/lunch/list");
	}
	
	@PostMapping("/lunch/edit/{bootId}")//수정에 2번째
	//(@Validated BootEditDTO bootEditDTO 요청본문이나 파라미터를 BootEditDTO객체에 바인딩 하고 
	//해당 객체에서 유효성 검사를 수행합니다
	//Errors errors 유효성 검사결과가 이객체에 담깁니다 에러가 존대하면  hasErrors()가 true를 리턴
	public ModelAndView update(@Validated BootEditDTO bootEditDTO, Errors errors) {
		if (errors.hasErrors()) {
			String errorMessage=					
			errors
			.getFieldErrors()
			.stream()
			.map(x -> x.getField() + 
			" : " + x.getDefaultMessage())
			.collect(Collectors.joining("\n"));
			//에러가 있다면 각필드의 에러 메세지를 필드명 : 메세지 형태로 만들어  \n으로 연결된 문자열로 
			//만듭니다
			return this.error422(
					errorMessage,
					String.format("/lunch/edit/%s", bootEditDTO.getBootId())
			);
			
		}
		
		this.bootService.update(bootEditDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/lunch/read/%s", bootEditDTO.getBootId()));
		return mav;
	}

	private ModelAndView error422(String errorMessage, String redirectUrl) {
		ModelAndView mav  = new ModelAndView("redirect" + redirectUrl);
        mav.addObject("error", errorMessage);

		return mav;
	}

	
	//delete 삭제 하고 리스트로 보내기..
	@PostMapping("/lunch/delete")
	public String delete(Integer bootId) throws NoSuchElementException{
		this.bootService.delete(bootId);
		return "redirect:/lunch/list";
	}

	
	//목록
	@GetMapping(value= {"/lunch/list", "/lunch"})
	public ModelAndView bootList(String title, Integer page, ModelAndView mav) {
		
		mav.setViewName("/lunch/list");
		List<BootListResponseDTO> boots = this.bootService.bootList(title, page);
		mav.addObject("boots", boots);
		return mav;
		/*
		자바 제네릭(Generic)은 클래스나 메서드에서 사용할 데이터 타입을 일반화(추상화)해서 정의하는 
		문법 입니다 
		
		어렵게 talk **타입을 변수처럼 쓰겠다
		
		제네릭이 없을경우
		List list = new ArrayList();
		list.add("hello");
		list.add(123);
		
		String s = (String) list.get();
		
		List<String> list = new ArrayList<>();
		
		public class Box<T>
		*/
	}
	
	
	
	
	
	
	
	
	
	

}
