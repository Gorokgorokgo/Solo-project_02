package com.sparta.calendar.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;
  private String userName;
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  @PrePersist
  public void uploadDate(){
    createdDate = LocalDateTime.now();
    modifiedDate = LocalDateTime.now();
  }

  @PreUpdate
  public void updateDate(){
    modifiedDate = LocalDateTime.now();
  }
}
