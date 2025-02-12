package com.example.schedulemanagementdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {}

    public Schedule(String title, String toDo) {
        this.title = title;
        this.toDo = toDo;
    }

    public void updateSchedule(String title, String toDo) {
        this.title = title;
        this.toDo = toDo;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateToDo(String toDo) {
        this.toDo = toDo;
    }
}