package com.giantstep.board.domain.board.repository.comment;

import com.giantstep.board.domain.board.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, BoardCommentRepositoryCustom, QuerydslPredicateExecutor<BoardComment> {
    public Optional<BoardComment> findBoardCommentByIdAndPassword(Long boardCommentId, String boardCommentPassword);
}
