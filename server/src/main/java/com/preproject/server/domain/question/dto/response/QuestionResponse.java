package com.preproject.server.domain.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionResponse {

    private Long questionId;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
