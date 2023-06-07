package com.preproject.server.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class QuestionDto {

    private Long questionId;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
