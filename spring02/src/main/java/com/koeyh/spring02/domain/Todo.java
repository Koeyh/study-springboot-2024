package com.koeyh.spring02.domain;

import java.time.LocalDateTime;

import lombok.Data;
// Getter, Setter, RequiredArgsConstructor 모두 포함된 어노테이션

@Data
public class Todo {
    private int tno;

    private String title;

    private LocalDateTime dueDate;

    private String writer;

    private int isDone;
}
