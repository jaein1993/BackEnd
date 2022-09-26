package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.DAOException;


public interface BoardMapper {	// MyBatis's Mapper Interface
	
	
	// 1. 게시판 테이블의 전체목록 조회하기
	@Select("SELECT /*+ index_desc(tbl_board) */ * FROM tbl_board")
	public abstract List<BoardVO> selectAllList() throws DAOException;
	
	//1-1새로이 페이징 처리가 적용된, 게시물 목록조회 하기  
	public abstract List<BoardVO> selectListWithPaging(Criteria cri) throws DAOException;
	
	// 2. 새로운 게시글을 등록하기(CREATE) - Mapper XML 파일로 처리
	public abstract Integer insert(BoardDTO dto) throws DAOException;
	public abstract Integer insertSelectKey(BoardDTO dto) throws DAOException;		// *** : 새로이 입력된 게시글의 bno를 반환받기를 원하는 경우
	
	// 3. 기존 게시글 상세조회 하기(READ) - Mapper XML 파일로 처리
	public abstract BoardVO select(BoardDTO dto) throws DAOException;
	
	// 4. 기존 게시글 수정하기(UPDATE) - Mapper XML 파일로 처리
	public abstract Integer update(BoardDTO dto) throws DAOException;
	
	// 5. 기존 게시글 삭제하기(DELETE)
	@Delete("DELETE FROM tbl_board WHERE bno = #{bno}")
	public abstract Integer delete(@Param("bno") Integer bno) throws DAOException;
	
	// 6. 총 게시물의 갯수를 반환
	public abstract Integer getTotalCount() throws DAOException;
	

} // end interface
