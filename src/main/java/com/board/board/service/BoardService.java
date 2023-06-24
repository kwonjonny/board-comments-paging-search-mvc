package com.board.board.service;

import com.board.board.dto.board.BoardCreateDTO;
import com.board.board.dto.board.BoardDTO;
import com.board.board.dto.board.BoardListDTO;
import com.board.board.dto.board.BoardUpdateDTO;
import com.board.board.dto.page.PageRequestDTO;
import com.board.board.dto.page.PageResponseDTO;

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
