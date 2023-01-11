package com.giantstep.board.domain.board.dto.comment;

import com.giantstep.board.domain.board.entity.BoardComment;
import lombok.Data;

@Data
public class BoardCommentUpdateFormDto {
    private Long boardCommentId;
    private String boardCommentContents;
    private String boardCommentPassword;

    public BoardComment toEntity() {
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentId(boardCommentId)
                .boardCommentContents(boardCommentContents)
                .boardCommentPassword(boardCommentPassword)
                .build();
        return boardComment;
    }
}
