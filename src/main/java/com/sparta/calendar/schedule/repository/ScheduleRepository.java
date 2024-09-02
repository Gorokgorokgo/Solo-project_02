package com.sparta.calendar.schedule.repository;

import com.sparta.calendar.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  // 날짜로 조회하는 메서드
  List<Schedule> findByDate(LocalDate date);

  // 페이징과 정렬을 위한 메서드
  Page<Schedule> findAllByOrderByModifiedDateDesc(Pageable pageable);
}
