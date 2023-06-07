package com.preproject.server.domain.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionRequest {

    private String title;
    private String body;
}
