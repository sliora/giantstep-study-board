package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.dto.comment.BoardCommentAddFormDto;
import com.giantstep.board.domain.board.dto.comment.BoardCommentDeleteCheckRequest;
import com.giantstep.board.domain.board.dto.comment.BoardCommentUpdateCheckRequest;
import com.giantstep.board.domain.board.service.BoardCommentService;
import com.giantstep.board.utils.UtilsMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("giantstep-study/board")
public class BoardCommentController extends UtilsMethod {

    private final BoardCommentService boardCommentService;

    @PostMapping("{boardId}/commentAdd")
    public String commentAddDone(@PathVariable("boardId") Long boardId, Model model,
                                 @ModelAttribute("boardComment")BoardCommentAddFormDto boardCommentAddFormDto){
        boardCommentService.saveBoardComment(boardId, boardCommentAddFormDto);
        return showMessageAndRedirectUri("댓글이 성공적으로 등록되었습니다.", "detail", model);
    }

    @ResponseBody
    @PostMapping("{boardId}/checkUpdateBoardCommentRequest")
    public Boolean commentUpdateDone(@PathVariable("boardId") Long boardId,
                                     @RequestBody BoardCommentUpdateCheckRequest boardCommentUpdateCheckRequest) {
        return boardCommentService.updateBoardComment(boardCommentUpdateCheckRequest.toEntity());
    }

    @ResponseBody
    @PostMapping("{boardId}/checkDeleteBoardCommentRequest")
    public Boolean commentDeleteDone(@PathVariable("boardId") Long boardId,
                                     @RequestBody BoardCommentDeleteCheckRequest boardCommentDeleteCheckRequest) {
        return boardCommentService.deleteBoardComment(boardCommentDeleteCheckRequest.getBoardCommentId(),
                boardCommentDeleteCheckRequest.getBoardCommentPassword());
    }
}
