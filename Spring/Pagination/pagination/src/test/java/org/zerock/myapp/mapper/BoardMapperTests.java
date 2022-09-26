package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
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
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.DAOException;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor


// For JUnit 4
//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)

// For JUnit Jupyter 5
@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardMapperTests {

	// BoardMapper 를 주입받아서, 테스트 수행
	@Setter(onMethod_= {@Autowired})
	private BoardMapper mapper;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.mapper);
		log.info("\t+ mapper: {}", this.mapper);
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. BoardMapper.selectAllList test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testSelectAllList() throws DAOException {
		log.trace("testSelectAllList() invoked.");
		
		@Cleanup("clear")
		List<BoardVO> list = this.mapper.selectAllList();
		
		for(BoardVO vo : list ) {
			log.info("\t+ vo : {}", vo);
		} // enhanced for
	} // testSelectAllList
	
	
////	@Disabled
//	@Test
//	@Order(2)
//	@DisplayName("2. BoardMapper.insert(dto) test.")
//	@Timeout(value=3, unit=TimeUnit.SECONDS)
//	void testInsert() throws DAOException {
//		log.trace("testInsert() invoked.");
//		
//		BoardDTO dto = new BoardDTO();
//		dto.setTitle("TITLE_NEW");
//		dto.setContent("CONTENT_NEW");
//		dto.setWriter("WRITER_NEW");
//		
//		int affectedLines = this.mapper.insert(dto);
//		log.info("\t+ affectedLines : {}", affectedLines);
//	} // testInsert
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. BoardMapper.insertSelectKey(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testInsertSelectKey() throws DAOException {
		log.trace("testInsertSelectKey() invoked.");
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle("TITLE_NEW");
		dto.setContent("CONTENT_NEW");
		dto.setWriter("WRITER_NEW");
		
		log.info("\t+ 1. dto: {}", dto);	
		log.info("\t+ 2. result : {}", this.mapper.insertSelectKey(dto) == 1);
		log.info("\t+ 3. dto: {}", dto);
	} // testInsertSelectKey
	
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("3. BoardMapper.select(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testSelect() throws DAOException {
		log.trace("testSelect() invoked.");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(299);
		
		log.info("\t+ BoardVO : {}", this.mapper.select(dto));
	} // testSelect
	
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("4. BoardMapper.update(dto) test.")
	@Timeout(value=30, unit=TimeUnit.SECONDS)
	void testUpdate() throws DAOException {
		log.trace("testUpdate() invoked.");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(79);
		dto.setTitle("TITLE_UPDATED");
		dto.setContent("CONTENT_UPDATED");
		dto.setWriter("WRITER_UPDATED");
		
		log.info("\t+ result : {}", this.mapper.update(dto) == 1);
	} // testUpdate
	

//	@Disabled
	@Test
	@Order(5)
	@DisplayName("5. BoardMapper.delete(bno) test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testDelete() throws DAOException {
		log.trace("testDelete() invoked.");
		
		log.info("\t+ result: {}", this.mapper.delete(177) == 1 );
	} // testDelete
	
	@Test
	@Order(6)
	@DisplayName("6.testSelectListWithPaging")
	void testSelectListWithPaging() throws DAOException {
		log.trace("testSelectListWithPaging() invoked.");
		
		
		Criteria cri = new Criteria();
		cri.setCurrPage(3);
		cri.setAmount(10);
		
		@Cleanup("clear")
		List<BoardVO> list = this.mapper.selectListWithPaging(cri);
		list.forEach(log::info);
		
	} // testSelectListWithPaging
	
	
	@Test
	@Order(7)
	@DisplayName("7.testGetTotalCount")
	@Timeout(value=50, unit=TimeUnit.SECONDS)
	void testGetTotalCount() throws DAOException {
		log.trace("testGetTotalCount() invoked.");
		
		log.info("\t+totalCount:{}",this.mapper.getTotalCount());

		
	} // testGetTotalCount

} // end class
