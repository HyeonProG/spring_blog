package com.tenco.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenco.blog.dto.SaveDTO;
import com.tenco.blog.handler.exception.DataDeliveryException;
import com.tenco.blog.handler.exception.RedirectException;
import com.tenco.blog.repository.interfaces.BoardRepository;
import com.tenco.blog.repository.model.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private final BoardRepository boardRepository;
	
	public List<Board> readBoardList() {
		List<Board> boardListEntity = null;
		
		try {
			boardListEntity = boardRepository.readAll();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataDeliveryException("잘못된 입력입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return boardListEntity;
	}
	
	public void createBoard(SaveDTO dto) {
		int result = 0;
		
		try {
			result = boardRepository.insert(dto.toBoard());
		} catch (DataDeliveryException e) {
			throw new DataDeliveryException("잘못된 입력입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		if (result == 0) {
			throw new DataDeliveryException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public Board readBoard(Integer id) {
		return boardRepository.findById(id);
	}
	
	public void updateBoard(Integer id, Board dto) {

		boardRepository.updateById(dto, id);
		
	}
	
	public void deleteBoard(Integer id) {
		boardRepository.deleteById(id);
	}
	
	public int countBoard() {
		return boardRepository.countBoard();
	}
	
	public List<Board> readBoardPagination(int page, int size) {
		List<Board> list = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		list = boardRepository.readBoardPagination(limit, offset);
		
		return list;
	}
	
}
