package org.zerock.myapp.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.ParamsDTO;
import org.zerock.myapp.domain.SampleDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


// 기본적으로 Spring beans container 에 등록될 클래스는 자바빈즈 규약을 지키는 "자바빈즈" 클래스이다.
@Log4j2
@NoArgsConstructor

//@RequestMapping("/board/*")			// Wildcard Base URI	==> if possible, do NOT use this!
@RequestMapping("/board/")			// Best Base URI

@Controller							// 현재의 클래스의 역할: Controller 정의
public class SampleController {
	
		
	//-------------------------------------------//
	// 1. @RequestMapping or @RequestMapping("")
	//-------------------------------------------//	
//	@RequestMapping("")			// Request URI == Base URI + Detail URI => "/board/" + "" => /board/
	@RequestMapping				// Request URI == Base URI + Detail URI => "/board/" => /board/
	public void basic() {
		log.trace("basic() invoked.");

		// 반환되는 뷰의 이름 = Request URI when return type is void.
	} // basic
	
	
	//-------------------------------------------//
	// 2. @RequestMapping(path, method)
	//-------------------------------------------//
	@RequestMapping(
//		path= { "/basicGet" },
//		method= { RequestMethod.GET }
		
		path= "/basicGet",
		method= RequestMethod.GET
	)
	public void basicGet() {
		log.trace("basicGet() invoked.");

		// 뷰의 이름 == Request URI ==> "/board/basicGet"
	} // basicGet
	
	
	//-------------------------------------------//
	// 3. @RequestMapping(path, method)
	//-------------------------------------------//
	@RequestMapping(
		path= { "/basicGetPost" },
		method = { RequestMethod.GET, RequestMethod.POST }
	)
	public void basicGetPost() {
		log.trace("basicGetPost() invoked.");

		
		// 뷰의 이름 == Request URI ==> "/board/basicGetPost"
	} // basicGetPost
	
	
	//-------------------------------------------//
	// 4. @GetMapping(path)
	//-------------------------------------------//
	@GetMapping("/basicOnlyGet")
//	@RequestMapping(path="/basicOnlyGet", method=RequestMethod.GET)
	public void basicOnlyGet() {
		log.trace("basicOnlyGet() invoked.");
		

		// 뷰의 이름 == Request URI ==> "/board/basicOnlyGet"
		// View Resolver가 생성한 실제 뷰의 경로 
		// => Prefix + viewName + Suffix = "/WEB-INF/views/board/basicOnlyGet.jsp"
	} // basicOnlyGet
	
	
	//-------------------------------------------//
	// 5. @PostMapping(path)
	//-------------------------------------------//
	@PostMapping("/basicOnlyPost")
	public void basicOnlyPost() {
		log.trace("basicOnlyPost() invoked.");


		// 뷰의 이름 == Request URI ==> "/board/basicOnlyPost"
	} // basicOnlyPost
	
	
	//-------------------------------------------//
	// 6. @GetMapping(path) with Request Parameters
	//-------------------------------------------//
	// 컨트롤러의 핸들러는 아래의 역할 및 값을 반환:
	//   (1) 역할: 요청을 위임받아 처리
	//   (2) 뷰의 이름을 반환
	//   (3) 역할에 따라, 요청을 위임처리한 결과 => Model(비지니스 데이터) 을 생성 및 View로 전달
	@GetMapping("/ex01")
	public String ex01(ParamsDTO dto) {				
		log.trace("ex01({}) invoked.", dto);
		
		// By View Resolver Configuration
		// Prefix + ViewName + Suffix = /WEB-INF/views/ + ex01 + .jsp = /WEB-INF/views/ex01.jsp
		return "ex01";		// MVC패턴에서, View의 이름
	} // ex01
	
	
	//-------------------------------------------//
	// 7. @GetMapping(path) with some primitive types's parameters
	//-------------------------------------------//
	// 컨트롤러의 핸들러 메소드의 매개변수로 기본타입을 사용하면 안됨(****)
	// 기본타입의 매개변수가 필요할시에는, 기본타입에 대응되는 wrapper type를 사용할 것!
	// 더불어, 우리가 지정하는 매개변수의 이름은 기본적으로 전송파라미터의 이름과 동일해야 함 =>
	// 그래야 DispatcherServlet이 이름에 맞는 전송파라미터의 값을 자동으로 넣어줌
	@GetMapping("/ex02")
	public String ex02(
			@RequestParam("name") String recvName,
			@RequestParam("age") Integer currentAge
		) {
		log.trace("ex02({}, {}) invoked.", recvName, currentAge);

		
		
		return "ex02";	// 반환된 뷰의이름
	} // ex02
	
	
	//-------------------------------------------//
	// 8. @GetMapping(path) with multi-values request parameters
	//-------------------------------------------//
	@GetMapping("/ex03")
//	public String ex03(List<String> hobby) {			// XX : java.lang.IllegalStateException
//	public String ex03(ArrayList<String> hobby) {		// OX : 오류는 발생안하는데, 실제 요소값이 안들어온다!!
	
//	public String ex03( @RequestParam("hobby") List<String> hobby ) {		// OK
//	public String ex03( @RequestParam("hobby") ArrayList<String> hobby) {	// OK
//	public String ex03( @RequestParam("hobby") Set<String> hobby ) {		// OK
	public String ex03( String[] hobby ) {									// OK
		log.trace("ex03({}) invoked.", Arrays.toString(hobby));
//		log.trace("ex03({}) invoked.", hobby);
		log.info("\t+ type: {}", hobby.getClass().getName());
		
		
		return "ex03";
	} // ex03
	
	
	//-------------------------------------------//
	// 9. @DateTimeFormat
	//-------------------------------------------//
	// 전송파라미터의 값이 날짜포맷형식으로 전달될 때,
	// 컨트롤러의 핸들러 메소드의 매개변수로 아예 처음부터, Date객체로 얻을 수 있다!!!
	
	@GetMapping("/ex04")
	public String ex04(
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date hireDate
		) {
		log.debug("ex04({}) invoked.", hireDate);
		
		
		return "ex04";
	} // ex04
	
	
	//-------------------------------------------//
	// 13. Predefined Model parameter 
	//-------------------------------------------//
	// To transfer request parameters into the View.
	//-------------------------------------------//	
	@GetMapping("/ex05")
	public String ex05(
			// 1. 각 전송파라미터의 값 획득 from DispatcherServlet
//			String name, Integer age,
			SampleDTO dto, Integer page,
			// 2. 핵심 비지니스 로직의 수행 결과데이터를 저장 및 View에 전달하는 상자(***)
			Model model
		) {
		log.trace("ex05({}, {}, {}) invoked.", dto, page, model);		
		log.info("\t+ model type: {}", model.getClass().getName());
		
		// 3. Model 상자에 저장할 객체 생성
//		SampleDTO dto = new SampleDTO();
//		dto.setName(name);
//		dto.setAge(age);
		
		// 비지니스 데이터를 저장하는 "상자" 역할을 하는 Model 객체
//		model.addAttribute("sampleDTO", dto);
		model.addAttribute("page", page);
		
		return "commandObject";	// 반환된 뷰의 이름
	} // ex05
	
	
	//-------------------------------------------//
	// 14. @ModelAttribute(key) to transfer data into the View.
	//-------------------------------------------//
	// (*Caution*) Command Object automatically transfered to the VIEW by Spring MVC.
	//	So, No need Model object if use Command Object in the VIEW.
	//	at this time, the name of the Command Object started with a lower-case of 
	//	the first character of the Command Object class name.
	//-------------------------------------------//
	@PostMapping("/ex06")
//	public String ex06(SampleDTO dto, Integer page) {	
	public String ex06(SampleDTO dto, @ModelAttribute("page") Integer page) {
		
		log.debug("ex06({}, {}) invoked.", dto, page);
		
		
		return "commandObject";
	} // ex06
	
	
	//-------------------------------------------//
	// 15. Predefined RedirectAttributes
	//-------------------------------------------//
	//	To redirect a request into the target url.
	//-------------------------------------------//
	@GetMapping("/ex07")
	public String ex07(
			String name, Integer age,
			// Model 상자와 비슷한 역할 - 임시상자역할 : 리다이랙션의 Target URL에 전달할 전송파라미터를
			//                                           만들어내는 역할
			RedirectAttributes rttrs
		) {
		log.debug("ex07({}, {}, {}) invoked.", name, age, rttrs);
		
		// 1. 1회성 데이터 전달 => Request Scope 에 공유속성으로 올려서 전달(공유)
//		rttrs.addFlashAttribute("name", name);
//		rttrs.addFlashAttribute("age", age);
		
		// 2. 전송파라미터로 전달 => GET 방식의 Query String 형태로 전달 (**** 권장 ****)
		rttrs.addAttribute("name", name);
		rttrs.addAttribute("age", age);

		// redirect: 가. 특수문자열 뒤에는, 재요청을 보낼 Target URL 지정
		//           나. 이 반환된 문자열은 View의 이름이 아니다! => View가 호출되지 않는다!
		//			 다. 바로 307 Redirect 응답문서가 즉시 전송됩니다.
		return "redirect:/board/ex02";
	} // ex07
	
	
	@GetMapping("/doNotUseThis")
	public void doNotUseThis(HttpServletRequest req, HttpServletResponse res, HttpSession ses) {
		log.trace("doNotUseThis(req, res, sess) invoked.");
		
		log.info("\t+ 1. req: {}", req);
		log.info("\t+ 2. res: {}", res);
		log.info("\t+ 3. ses: {}", ses);
		
		
		
	} // doNotUseThis
	
	
	
	
} // end class
