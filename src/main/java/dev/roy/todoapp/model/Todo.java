package dev.roy.todoapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

    private Long id;
    private String title;
    private String description;
    private LocalDate targetDate;
    private boolean status;
    private int uid;

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Todo(String title, String description, LocalDate targetDate, boolean status) {
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
//        this.uid = uid;
    }

    public Todo(Long id, String title, String description, LocalDate targetDate, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
//        this.uid = uid;
    }
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Todo todo = (Todo) o;
//        return id.equals(todo.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
