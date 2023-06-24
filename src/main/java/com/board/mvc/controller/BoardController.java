package com.board.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.mvc.dto.board.BoardCreateDTO;
import com.board.mvc.dto.board.BoardDTO;
import com.board.mvc.dto.board.BoardListDTO;
import com.board.mvc.dto.board.BoardUpdateDTO;
import com.board.mvc.dto.page.PageRequestDTO;
import com.board.mvc.dto.page.PageResponseDTO;
import com.board.mvc.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/")
public class BoardController {
    
    // 의존성 주입 
    private final BoardService boardService;

    // Autowried 명시적 표시 
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

     // GET : createBoard
    @GetMapping("create")
    public String getCreate() {
        log.info("GET | board/create");
        return "board/create";
    }

    // GET : listBoard
    @GetMapping("list")
    public String getList(PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | board/list");
        PageResponseDTO<BoardListDTO> boardDTO =  boardService.listBoard(pageRequestDTO);
        model.addAttribute("boardDTO", boardDTO);
        return "board/list";
    }

    // GET : readBoard
    @GetMapping("read/{tno}")
    public String getRead(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | board/read");
        BoardDTO boardDTO = boardService.readBoard(tno);
        model.addAttribute("boardDTO", boardDTO);
        return "board/read";
    }

    // GET : updateBoard
    @GetMapping("update/{tno}")
    public String getUpdate(@PathVariable("tno") Long tno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("GET | board/update");
        BoardDTO boardDTO = boardService.readBoard(tno);
        model.addAttribute("boardDTO", boardDTO);
        return "board/update";
    }

    // POST : createBoard
    @PostMapping("create")
    public String postCreate(BoardCreateDTO boardCreateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | board/create");
        int insertCount = boardService.createBoard(boardCreateDTO);
        redirectAttributes.addFlashAttribute("message", insertCount + " 번 : 게시물 등록이 완료되었습니다");
        return "redirect:/board/list";    
    }

    // POST : deleteBoard
    @PostMapping("delete/{tno}")
    public String postDelete(@PathVariable("tno") Long tno, RedirectAttributes redirectAttributes) {
        log.info("POST | board/create");
        boardService.deleteBoard(tno);
        redirectAttributes.addFlashAttribute("mesaage", "게시물 삭제가 완료되었습니다");
        return "redirect:/board/list";
    }

    // POST : updateBoard
    @PostMapping("update")
    public String postUpdate(BoardUpdateDTO boardUpdateDTO, RedirectAttributes redirectAttributes) {
        log.info("POST | board/update");
        boardService.updateBoard(boardUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "게시물 업데이트가 완료되었습니다");
        return "redirect:/board/read/" + boardUpdateDTO.getTno();
    }
}
