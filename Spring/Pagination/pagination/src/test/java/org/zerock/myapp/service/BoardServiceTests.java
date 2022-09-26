package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
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
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.ServiceException;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/spring/root-context.xml")


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	@BeforeAll
	void beforeall() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(this.service);
		log.info("\t+service:({})",this.service);		
		
	}//beforeall
	
	@Test
	@Order(1)
	@DisplayName("1. BoardSErvice.getList")
	@Timeout(value=100,unit=TimeUnit.SECONDS)
	void testGetList() throws ServiceException {
		log.trace("testGetList() invoked");
		
		@Cleanup("clear")
		List<BoardVO> list=this.service.getList();
		list.forEach(log::info);
		
	}
	
	
	@Test
	@Order(2)
	@DisplayName("2.BoardService.register")
	@Timeout(value=100,unit=TimeUnit.SECONDS)
	void testRegister() throws ServiceException {
		
		BoardDTO dto =new BoardDTO();
		dto.setTitle("TITLE_NEW");
		dto.setContent("CONTENT_NEW");
		dto.setWriter("WRITER_NEW");
		
		
		log.info("\t+result:({})",this.service.register(dto));
	}
	
	@Test
	@Order(3)
	@DisplayName("3.BoardService.modify")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testModify() throws ServiceException{
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(77);
		dto.setTitle("TITLE_UPDATED");
		dto.setContent("CONTENT_UPDATED");
		dto.setWriter("WRITER_UPDATED");
		
		log.info("\t+result:({})",this.service.modify(dto));
		
		
	}
	@Test
	@Order(4)
	@DisplayName("4.BoardService.remove")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testRemove() throws ServiceException{
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(151);

		
		log.info("\t+result:({})",this.service.remove(dto));
	}//remove
	
	@Test
	@Order(5)
	@DisplayName("5.BoardService.get")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGet() throws ServiceException{
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(170);

		
		log.info("\t+result:({})",this.service.get(dto));
	}//get
	
	@Test
	@Order(6)
	@DisplayName("6.GetListPerPage ")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetListPerPage() throws ServiceException{
		
		Criteria cri = new Criteria();
		cri.setCurrPage(2);
		cri.setAmount(10);
		
		@Cleanup("clear")
		List<BoardVO> list=this.service.getListPerPage(cri);
		list.forEach(log::info);
	}
	
	@Test
	@Order(7)
	@DisplayName("7.getTotal")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void testGetTotal() throws ServiceException{
		
		log.trace("testGetTotal() invoked");
		
		log.info("\t+total:({})",this.service.getTotal());
	}//getTotal
	
	
		
		

	
	
	
	
	
}
