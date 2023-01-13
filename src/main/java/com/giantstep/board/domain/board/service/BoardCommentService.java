package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.comment.BoardCommentAddFormDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.entity.BoardComment;
import com.giantstep.board.domain.board.repository.board.BoardRepository;
import com.giantstep.board.domain.board.repository.comment.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    public Long saveBoardComment(Long boardId, BoardCommentAddFormDto boardCommentAddFormDto) {

        Board findBoard = boardRepository.findById(boardId).get();
        boardCommentAddFormDto.setBoard(findBoard);
        BoardComment boardComment = boardCommentAddFormDto.toEntity();
        Long saveBoardCommentId = boardCommentRepository.save(boardComment).getId();

        return saveBoardCommentId;
    }

    @Transactional
    public Boolean deleteBoardComment(Long boardCommentId, String deleteBoardCommentPassword) {

        //AtomicReference : CAS(compare-and-swap)를 이용하여 자바의 동시성을 보장하는 클래스.
        AtomicReference<Boolean> successDeleteBoardComment = new AtomicReference<>(false);

        //삭제할 댓글의 비밀번호 검증
        Optional<BoardComment> deleteBoardCommentCheckPassword = boardCommentRepository.findBoardCommentByIdAndPassword(boardCommentId, deleteBoardCommentPassword);

        //Optional<T> ifPresent() : Optional 객체가 값을 가지고 있으면 실행
        deleteBoardCommentCheckPassword.ifPresent(object->{
             successDeleteBoardComment.set(boardCommentRepository.findById(deleteBoardCommentCheckPassword.get().getId()).get().deleteBoardComment());
        });

        return successDeleteBoardComment.get();
    }

    @Transactional
    public Boolean updateBoardComment(BoardComment updateBoardComment) {

        AtomicReference<Boolean> checkUpdateBoardCommentRequestFlag = new AtomicReference<>(false);

        Optional<BoardComment> updateBoardCommentCheckPassword = boardCommentRepository.findBoardCommentByIdAndPassword(updateBoardComment.getId(), updateBoardComment.getPassword());

        updateBoardCommentCheckPassword.ifPresent(object->{
            checkUpdateBoardCommentRequestFlag.set(boardCommentRepository.findById(updateBoardCommentCheckPassword.get().getId()).get().updateBoardComment(updateBoardComment));
        });

        return checkUpdateBoardCommentRequestFlag.get();
    }
}
