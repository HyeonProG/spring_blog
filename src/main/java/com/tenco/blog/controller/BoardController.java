package com.tenco.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.blog.dto.SaveDTO;
import com.tenco.blog.handler.exception.DataDeliveryException;
import com.tenco.blog.repository.model.Board;
import com.tenco.blog.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	/**
	 * 게시글 페이지
	 * 
	 * @param model
	 * @return main.jsp
	 */
	@GetMapping({ "/main", "/" })
	public String index(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		System.out.println("mainPage()");
		List<Board> boardList = boardService.readBoardPagination(page, size);

		if (boardList.isEmpty()) {
			model.addAttribute("boardList", null);
		} else {
			model.addAttribute("boardList", boardList);
		}

		// 페이지 개수를 계산하기 위해서 총 페이지 수를 계산해야 한다.
		int totalRecords = boardService.countBoard();
		int totalPages = (int) Math.ceil((double) totalRecords / size);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "main";
	}

	/**
	 * 게시글 생성 페이지
	 * 
	 * @return saveForm.jsp
	 */
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	/**
	 * 게시글 생성
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/board/save")
	public String saveProc(SaveDTO dto) {

		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new DataDeliveryException("제목을 입력하세요.", HttpStatus.BAD_REQUEST);
		}

		if (dto.getTitle().length() > 20 || dto.getContent().length() > 20 || dto.getAuthor().length() > 20) {
			throw new DataDeliveryException("20글자 이내로 작성해 주세요", HttpStatus.BAD_REQUEST);
		}

		if (dto.getContent() == null || dto.getContent().isEmpty()) {
			throw new DataDeliveryException("내용을 입력하세요.", HttpStatus.BAD_REQUEST);
		}

		if (dto.getAuthor() == null || dto.getAuthor().isEmpty()) {
			throw new DataDeliveryException("작성자를 입력하세요.", HttpStatus.BAD_REQUEST);
		}

		boardService.createBoard(dto);

		return "redirect:/main";
	}

	/**
	 * 게시글 수정 페이지
	 * 
	 * @param id
	 * @return updateForm.jsp
	 */
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable(name = "id") int id) {
		System.out.println("1111");
		return "board/updateForm";
	}

	/**
	 * 게시글 수정
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	@PostMapping("/board/{id}/update")
	public String update(@PathVariable(name = "id") int id, Board dto) {
		System.out.println("2222");
		Board board = boardService.readBoard(id);
		board.setTitle(dto.getTitle());
		board.setContent(dto.getContent());
		System.out.println("3333");
		boardService.updateBoard(id, dto);

		return "redirect:/main";
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/board/{id}/delete")
	public String delete(@PathVariable(name = "id") int id) {

		boardService.deleteBoard(id);

		return "redirect:/main";
	}
}
