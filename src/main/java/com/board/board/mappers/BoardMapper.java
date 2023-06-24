package com.board.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.board.dto.board.BoardCreateDTO;
import com.board.board.dto.board.BoardDTO;
import com.board.board.dto.board.BoardListDTO;
import com.board.board.dto.board.BoardUpdateDTO;
import com.board.board.dto.page.PageRequestDTO;

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
