package com.giantstep.board.domain.board.dto.comment;

import lombok.Data;

@Data
public class BoardCommentDeleteCheckRequest {
    private Long boardCommentId;
    private String boardCommentPassword;
}