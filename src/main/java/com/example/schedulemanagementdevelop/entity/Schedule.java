package com.example.schedulemanagementdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String toDo;

    @Column(nullable = false)
    private String username;

    public Schedule() {}

    public Schedule(String title, String toDo, String username) {
        this.title = title;
        this.toDo = toDo;
        this.username = username;
    }

    public void updateSchedule(String title, String toDo, String username) {
        this.title = title;
        this.toDo = toDo;
        this.username = username;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateToDo(String toDo) {
        this.toDo = toDo;
    }
}