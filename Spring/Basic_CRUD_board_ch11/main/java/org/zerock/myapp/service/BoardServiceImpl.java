package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor 

@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

	@Override
	public List<BoardVO> getList() throws ServiceException {
		log.trace("getList({}) invoked");
		try {
//			List<BoardVO> list=this.mapper.selectAllList();
//			return list;
			return this.mapper.selectAllList();
		}catch(Exception e) {
			throw new ServiceException(e);	
		}
	}//list

	@Override
	public boolean register(BoardDTO dto) throws ServiceException {
		try {
		return this.mapper.insertSelectKey(dto)==1; //참
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//reister

	@Override
	public boolean modify(BoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked",dto); 
		try {
		return this.mapper.update(dto)==1; //참
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//modify

	@Override
	public boolean remove(BoardDTO dto) throws ServiceException {
		log.trace("remove({}) invoked",dto);
		try {
		return this.mapper.delete(dto.getBno())==1; //참
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//remove

	@Override
	public BoardVO get(BoardDTO dto) throws ServiceException {
		log.trace("get({}) invoked",dto);
		try {
		return this.mapper.select(dto); //상세조회이기때문에 null이던뭐던 그냥 그대로 반환하게 하기 
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}//get

}
