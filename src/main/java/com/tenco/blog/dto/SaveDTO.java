package com.tenco.blog.dto;

import com.tenco.blog.repository.model.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SaveDTO {

	private String title;
	private String content;
	private String author;
	
	public Board toBoard() {
		return Board.builder()
				.title(this.title)
				.content(this.content)
				.author(this.author)
				.build();
	}
	
}
