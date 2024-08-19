package com.tenco.blog.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.blog.repository.model.Board;

@Mapper
public interface BoardRepository {

	public int insert(Board board);
	
	public int updateById(@Param("board") Board board, @Param("id") Integer id);
	
	public int deleteById(Integer id);
	
	public Board findById(Integer id);
	
	public List<Board> readAll();
	
	public List<Board> readBoardPagination
	(@Param("limit") int limit,
			@Param("offset") int offset);
	
	public int countBoard();
	
}
