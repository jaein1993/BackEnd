package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/board/")
@Controller
//public class BoardController implements InitializingBean{
public class BoardController{	
	//스프링4.3이후부터는,주입받을필드가 1개이고, 이 필드1개를 매개변수로 가지는 
	//생성자를 만들면, 주입시그널을 보내지 않아도, 자동으로 주입을 해줍니다.
	
	 //-- 생성자를 이용한 BoardService 주입 
	private BoardService service;
	
	
	//전체 게시글을 조회 
	@GetMapping("/list")
	public void list(Model model) throws ControllerException {
		log.trace("list() invoked");
		try {
		List<BoardVO> list=this.service.getList();
		
		
		model.addAttribute("list",list);  //JSP로 전달할 모델 데이터를 상자에 넣음 
	
		
		}catch(Exception e) {
			throw new ControllerException(e);
		}
		
	}//list
	
	   //----------------------------
	   //-- 2. 게시물을 등록 (등록처리만 함 ) 
	   //----------------------------
	   @PostMapping("/register")
	   public String register(BoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
	      log.trace("register() invoked.");
	      
	      try {
	         
	         boolean isRegister = this.service.register(dto);
	         log.info("\t+ isRegister", isRegister);
	         
	         rttrs.addAttribute("result", isRegister ? "SUCCESS(" + dto.getBno() + ")" : "FAILURE");
	         
	         return "redirect:/board/list";
	          
	      } catch (Exception e) {
	         throw new ControllerException(e);
	      }
	      
	   } // register
	   
//	   @GetMapping("/register")//화면을 달라 
//	   public void register() {
//		   log.trace("register() invoked");
//	   }//register
	   
	   
	   //----------------------------
	   //-- 3. 게시물을 수정
	   //----------------------------
	   @PostMapping("/modify")
	   public String modify(BoardDTO dto,RedirectAttributes rttrs) throws ControllerException{
		   log.trace("modify() invoked");
		   
		   try {
			boolean isModify=this.service.modify(dto);
	         log.info("\t+ isModify", isModify);
	         
	         rttrs.addAttribute("result", isModify ? "SUCCESS(" + dto.getBno() + ")" : "FAILURE");
	         
	         return "redirect:/board/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		   
		  
	   }//modify

	      
	   //----------------------------
	   //-- 4. 게시물을 삭제 +수정페이지도 똑같아서 같이 
	   //----------------------------
	   
	   @PostMapping("/remove")
	   public String remove(BoardDTO dto,RedirectAttributes rttrs) throws ControllerException{
		   log.trace("remove() invoked");
		   
		   try {
			boolean isRemove=this.service.remove(dto);
	         log.info("\t+ isRemove", isRemove);
	         
	         rttrs.addAttribute("result", isRemove ? "SUCCESS(" + dto.getBno() + ")" : "FAILURE");
	         
	         return "redirect:/board/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	   }//remove 
		   //----------------------------
		   //--5.기존게시글 상세 조회
		   //----------------------------
		   
		   @GetMapping({"/get","/modify"})
		   public void get(BoardDTO dto,Model model) throws ControllerException{
			   log.trace("get() invoked");
			   
			   try {
				BoardVO vo=this.service.get(dto);
		         log.info("\t+vo", vo);
		         
		         model.addAttribute("board", vo);
		         
		        
			} catch (Exception e) {
				throw new ControllerException(e);
			}
		   }//get

	 
	

//=====================================================================
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		log.trace("afterPropertiesSet() invoked");
//		assert this.service != null;
//		
//		log.info("\t+this.service:{}",this.service);
//		
//	}
	
	
	

}
