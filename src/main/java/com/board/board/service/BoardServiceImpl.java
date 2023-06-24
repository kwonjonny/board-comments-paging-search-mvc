package com.board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.board.dto.board.BoardCreateDTO;
import com.board.board.dto.board.BoardDTO;
import com.board.board.dto.board.BoardListDTO;
import com.board.board.dto.board.BoardUpdateDTO;
import com.board.board.dto.page.PageRequestDTO;
import com.board.board.dto.page.PageResponseDTO;
import com.board.board.mappers.BoardMapper;

// BoardService 구현체 클래스 
@Service
public class BoardServiceImpl implements BoardService {

    // 의존성 주입 
    private final BoardMapper boardMapper;

    // Autowired 명시적 표시 
    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    
    // Create Board ServiceImpl
    @Override
    @Transactional
    public int createBoard(BoardCreateDTO boardCreateDTO) {
      return boardMapper.createBoard(boardCreateDTO);
    }


    // List Board ServiceImpl
    @Override
    @Transactional
    public PageResponseDTO<BoardListDTO> listBoard(PageRequestDTO pageRequestDTO) {

        // 검색 조건 설정 
        String searchType = pageRequestDTO.getSearchType();
        String keyword = pageRequestDTO.getKeyword();

        List<BoardListDTO> list = boardMapper.listBoard(pageRequestDTO);
        int total = boardMapper.total(pageRequestDTO);

        return PageResponseDTO.<BoardListDTO>withAll()
                .list(list)
                .total(total)
                .build();
    }


    // Read Board ServiceImpl
    @Override
    @Transactional
    public BoardDTO readBoard(Long tno) {
        return boardMapper.readBoard(tno);
    }


    // Delete Board ServiceImpl
    @Override
    @Transactional
    public void deleteBoard(Long tno) {
       boardMapper.deleteBoard(tno);
    }


    // Update Board ServiceImpl
    @Override
    @Transactional
    public void updateBoard(BoardUpdateDTO boardUpdateDTO) {
        boardMapper.updateBoard(boardUpdateDTO);
    }
    
}
