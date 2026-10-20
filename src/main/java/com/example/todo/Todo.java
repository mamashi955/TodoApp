package com.example.todo;

import java.time.LocalDate;

public class Todo {
    private int id;
    private String content;
    private String user;
    private LocalDate deadline;
    private LocalDate createdAt;

    public Todo(int id, String content, String user,
                LocalDate deadline, LocalDate createdAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUser() {
        return user;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}