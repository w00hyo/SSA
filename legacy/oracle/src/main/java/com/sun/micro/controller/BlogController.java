package com.sun.micro.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sun.micro.vo.BlogListRequestVO;
import com.sun.micro.vo.BlogListResponseVO;

import com.sun.micro.service.BlogService;
import com.sun.micro.vo.BlogEditRequestVO;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;
	
	@GetMapping("/")
	public String home() {//views에서 home.jsp를 찾는다
		return "home";
	}
	
	//쓰기
	@GetMapping("/create")
	public String create() {//views에서 create.jsp를 찾는다
		return "create";
	}
	
    @PostMapping("/create")
    public String postCreate(@RequestParam Map<String, Object> map){
//클라이언트가 보낸 HTTP요청의 파라미터(formdata)를 모두 map받습니다
        int blogContSeq = this.blogService.create(map);
        /*
      게시글 관련 로직을 받는 서비스 클래스 
      보통은 insert수행ㅎㅏ고 저장된 게시글의 고유번호 pk를 리턴합니다 
        */
        return "redirect:/read/" + String.valueOf(blogContSeq);
        //저장이 끝나면 다시 read 페이지로 이동합니다
    }
    
    //읽기 read/{11} blogContSeq
    @GetMapping(value="/read/{blogContSeq}")//HTTP GET요청를 처리하는 메서드
    public String getRead(@PathVariable("blogContSeq")int blogContSeq, Model model) {
    	/*
@PathVariable("blogContSeq")int blogContSeq
url경로중에 {blogContSeq}가져와서 매서드 매개변수  blogContSeq 에 주입합니다   
read/-1 요청이 오면
blogContSeq는 값이 -1
 Model model => 컨트롤러에서 뷰로 데이터를 전달하기 위한 객체 
 model.addAttribute("이름", 값)으로 데이터를 저장하면
 ${이름}으로 접근할수 있습니다
Map<String, Object> blogCont = this.blogService.read(blogContSeq);	
아마도 비즈니스 로직을 담당하는 service클래스의 객체
반환갑시 Map<String, Object>인 것으로 보아
하나의 게시글 데이터를 키 값 
{
    "title": "스프링 MVC 예제",
    "content": "스프링 MVC 구조에 대해 설명합니다.",
    "writer": "홍길동",
    "regDate": "2025-10-30"
}
    	*/
    	Map<String, Object> blogCont = this.blogService.read(blogContSeq);
    	model.addAttribute("blogCont", blogCont);
    	return "/read";
    }
    
    /*수정에 프로세스
    1) 작성한 글을 읽어 온다
    2) 작성할 글을 수정한다
    3) read나 list로 리턴한다
    * */
    @GetMapping(value="/edit/{blogContSeq}")
    public ModelAndView getEdit(@PathVariable("blogContSeq") int blogContSeq){
        ModelAndView mav = new ModelAndView("/edit");
        Map<String, Object> blogCont = this.blogService.read(blogContSeq);

        if (blogCont == null) {
            mav.setViewName("redirect:/list");
            return mav;
        }

        mav.addObject("blogCont", blogCont);
        return mav;
    }

    //edit2
    @PutMapping("/edit/{blogContSeq}")
    public String putEdit(@PathVariable("blogContSeq") int blogContSeq, BlogEditRequestVO blogEditRequestVO){
    	//url값에 VO 세팅
    	blogEditRequestVO.setBlogContSeq(blogContSeq);
    	
    	
        boolean isSuccessEdit = this.blogService.edit(blogEditRequestVO);

        if(isSuccessEdit){
            //return "redirect:/edit/" + String.valueOf(blogEditRequestVO.getBlogContSeq());
        	return "redirect:/list";
        }

        return "redirect:/edit/" + blogContSeq;//실패시
    }
    /*
    public String putEdit(BlogEditRequestVO blogEditRequestVO)
매개변수: BlogEditRequestVO blogEditRequestVO
클라이언트가 보낸 수정 요청 데이터(폼 데이터 또는 JSON)가 여기에 바인딩됩니다.
Spring은 자동으로 HTTP 요청의 Body 혹은 Form 파라미터를 VO 객체에 매핑합니다.
예를 들어, title, content 등의 필드가 들어 있을 가능성이 큽니다.

단, @ModelAttribute나 @RequestBody 애너테이션이 생략되어 있는데,
요청 형태에 따라 추가가 필요할 수 있습니다.
폼 전송(application/x-www-form-urlencoded)이면 @ModelAttribute 생략 가능.
JSON(application/json)이면 @RequestBody를 명시해야 합니다.

리턴 타입: String
뷰 리졸버(ViewResolver)에게 반환할 뷰 이름 또는 리다이렉트 경로를 의미합니다.

3. 내부 로직 분석
java
코드 복사
boolean isSuccessEdit = this.blogService.edit(blogEditRequestVO);
blogService의 edit() 메서드를 호출해서 수정 로직을 처리합니다.

결과는 true 또는 false로 반환됩니다.
성공 시: true
실패 시: false
이 시점에서 실제 DB의 수정(update) 작업이 수행되었을 가능성이 높습니다.
    * */

    @DeleteMapping(value="/delete")
    public String delete(int blogContSeq){
        this.blogService.delete(blogContSeq);
        return "redirect:/list";
    }
    
    @GetMapping(value="/list") //HTTP get요청을 처리하겠다는 의미
    public String list(BlogListRequestVO blogListRequestVO, Model model) {
    	//이메서드는 반환형이 스트링 입니다 BlogListRequestVO blogListRequestVO 사용자가 
    	// /list로 요청을 보낼때 전달한 요청 파라미터를 자동으로 받아서 객체에 매핑합니다
    	// Model model 컨트롤러에서 뷰로 데이터를 전달하기 위한객체 
    	
//model에 속성을 추가 하는데 blogListRequestVO변수명을 가진 blogListRequestVO변수를 추가
    	
    	//모델에 데이터를 담는 코드
    	model.addAttribute("blogListRequestVO", blogListRequestVO);
		 //실제 서비스 계층을 호출해서 blogListRequestVO 조건에 맞는 블로그 목록 데이터를 조회하는 부분
		List<BlogListResponseVO> blogListResponseVOList = this.blogService.list(blogListRequestVO);
		
		//조회한 목록 데이터를 담습니다
		model.addAttribute("blogListResponseVOList", blogListResponseVOList);
		
		
		//System.out.println("test===============");
		//이런것들을 다 담아서 list로 리턴합니다
		return "/list";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
