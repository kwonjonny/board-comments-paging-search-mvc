package com.board.mvc.service;

import com.board.mvc.dto.board.BoardCreateDTO;
import com.board.mvc.dto.board.BoardDTO;
import com.board.mvc.dto.board.BoardListDTO;
import com.board.mvc.dto.board.BoardUpdateDTO;
import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;

// BoardService interface 
public interface BoardService {

    // Create Board Service 
    int createBoard(BoardCreateDTO boardCreateDTO);
    
    // List Board Service
    PageResponseDTO<BoardListDTO> listBoard(PageRequestDTO pageRequestDTO);

    // Read Board Service
    BoardDTO readBoard(Long tno);

    // Delete Board Service
    void deleteBoard(Long tno);

    // Update Board Service 
    void updateBoard(BoardUpdateDTO boardUpdateDTO);
}
