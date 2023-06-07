package com.preproject.server.domain.vote.entity;

import com.preproject.server.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vote extends Auditable {

    @Id
    @GeneratedValue
    private Long voteId;
}
