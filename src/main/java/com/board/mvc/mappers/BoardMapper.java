package com.board.mvc.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.mvc.dto.board.BoardCreateDTO;
import com.board.mvc.dto.board.BoardDTO;
import com.board.mvc.dto.board.BoardListDTO;
import com.board.mvc.dto.board.BoardUpdateDTO;
import com.board.mvc.dto.page.PageRequestDTO;

// BoardMapper interface 
@Mapper
public interface BoardMapper {
    
    // Create Board 
    int createBoard(BoardCreateDTO boardCreateDTO);
    
    // List Board 
    List<BoardListDTO> listBoard(PageRequestDTO pageRequestDTO);

    // Read Board 
    BoardDTO readBoard(Long tno);

    // Delete Board
    int deleteBoard(Long tno);

    // Update Board 
    int updateBoard(BoardUpdateDTO boardUpdateDTO);

    // Total Board 
    int total(PageRequestDTO pageRequestDTO);

}
