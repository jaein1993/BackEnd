package org.zerock.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//Spring 빈즈 컨테이너 구동시키는 어노테이션 
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/**/*-context.xml"})

//Spring MVC Framwork 구동시키는 어노테이션
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardControllerTests { 
	
	
	//WAS구동없이 테스트하려는 대상이 BoardController이니,
	//이 BoardController를 주입받아, 메소드 호출로 테스트해야지==>완전히 틀린것!!
	//	@Setter(onMethod_= {@Autowired})
//	private BoardController controller;
	
	// Spring Beans Container(type: WebApplicationContext) 자체를 주입 
	//(WAS구동없이 테스트하기위해서이다)
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(this.ctx);
		
		log.info("\t+ctx:{}",this.ctx);
		 
	}//beforeall
	
	@Test
	@Order(1)
	@DisplayName("1. BoardController.list")
	@Timeout(value=30,unit=TimeUnit.SECONDS)
	void testList() throws Exception {
		log.trace("testList() invoked");
		
//================================================================================		
//		//1.
//		//WAS 구동없이, Controller의 핸들러 메소드를 테스터하려면,  spring-test의존성에 포함된
//		//MockMvc객체가 필요합니다. 이 MockMvc객체를 생성하기위해서 , 바로 위에서 주입받은 
//		//Spring Beans Container가 필요합니다.
//		
//		//2. 아래의 MockMvc의 메소드들은 fluent-api, method-chaining 기법으로 사용하게 됨 
//		
//		//Step. 1 MockMvcBuilder 객체 얻기
//		MockMvcBuilder mockMvcBuilder= MockMvcBuilders.webAppContextSetup(ctx);
//		
//		
//		//Step. 2.MockMvc 객체 생성하기  
//		MockMvc mockMvc= mockMvcBuilder.build();
//		 
//		//Step 3. RequestBuilder 객체 생성하기 
//		//-(1)전송 파라미터가 없는 경우에는 아래와 같이 하고 
//		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list"); 
//		
//		//(2) 전송 파라미터까지 보내야하는겨우에는 아래와 같이 함
////		MockHttpServletRequestBuilder  reqBuilder =MockMvcRequestBuilders.get("/board/list");
//////		reqBuilder.param("전송파라미터이름,전송값들(가변인자))"
////	    reqBuilder.param("name","Jaein");
////	    reqBuilder.param("age","30");
//	    
//		
//		//Step 4. 실제 Controller로 요청(request)보내기
//		ResultActions resultActions=mockMvc.perform(reqBuilder);
//		//요청을 보낸 결과로, Model&view이름을 받을수있음 
//		
//		//Step 5. setp4에서 발생한 Model & View 이름을 얻어낸다.
//		MvcResult mvcResult=resultActions.andReturn();
//		
//		//Step6  Step5에서 얻어낸 MvcResult의 getter메소드를 이용해서
//		// Test Target 인 BoardController's handler메소드의 수행결과(뷰이름+모델)를 얻어냄
//		
//		ModelAndView modelAndView =mvcResult.getModelAndView();
//		
//		//Step 7 ModelAndView 객체로부터, 아래의 2가지 정보를 획득하여 출력
//		//(1) 반환된 뷰의 이름 92) 반환된 비즈니스 데이터 (즉, Model객체)
//		String viewName=modelAndView.getViewName();
//		ModelMap model = modelAndView.getModelMap();
//		
//		log.info("\t+viewName:{}",viewName);
//		log.info("\t+model:{}",model);
	//===================================================================================	
		MockMvcBuilder mockMvcBuilder =MockMvcBuilders.webAppContextSetup(ctx);
		//스프링 빈즈 컨테이너 설정
		
		MockMvc mockMvc=mockMvcBuilder.build();
		//MockMvc설정
		
		 MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/board/list"); 
		 
		 requestBuilder.param("currPage", "1");
		 requestBuilder.param("amount", "10");
		 
		 
		 //여기까지는 똑같음
		 //이제 Fluent-API 기반의 Method Chaining을 이용하여 간단하게 요 청에 대한 응답결과 획득
		 ModelAndView modelAndView=
				 mockMvc.
				 	perform(requestBuilder)
				 	.andReturn()
				 	.getModelAndView();
		 
		 log.info("\t+modelAndView:{}",modelAndView);

		
	}//testList();
	
	@Test
	@Order(2)
	@DisplayName("2.testGet")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	public void testGet() throws Exception{
		log.debug("testGe() invoked");
		
		
		MockMvcBuilder mockMvcBuilder =MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc=mockMvcBuilder.build();
		
		 MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/board/get");
		
		 requestBuilder.param("bno", "84");
		 requestBuilder.param("currPage", "3");
		 
		 ModelAndView modelAndView=
				 mockMvc. 
				 	perform(requestBuilder)
				 	.andReturn()
				 	.getModelAndView();
		 
		 log.info("\t+modelAndView:{}",modelAndView);
		
				
	}//testGet
	
	 
	
	
	

}
