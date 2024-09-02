package com.sparta.calendar.schedule.controller;

import com.sparta.calendar.schedule.entity.Schedule;
import com.sparta.calendar.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @PostMapping("/calendar")
  public Schedule upload(@RequestBody Schedule schedule) {
    return scheduleService.createSchedule(schedule);
  }

  // 일정 페이징 조회
  @GetMapping("/calendar")
  public Page<Schedule> getSchedules(
      @RequestParam(defaultValue = "0") int page,   // 페이지 번호
      @RequestParam(defaultValue = "10") int size) { // 페이지 크기, 디폴트 값은 10
    if (page < 0 || size <= 0) {
      throw new IllegalArgumentException("페이지 번호와 크기는 음수일 수 없습니다.");
    }
    return scheduleService.getSchedules(page, size);
  }

  @PutMapping("/calendar/{id}")
  public Schedule updateSchedule(
      @PathVariable Long id,
      @RequestParam LocalDate date,
      @RequestBody Schedule schedule) {
    return scheduleService.updateSchedule(date, id, schedule);
  }

  @DeleteMapping("/calendar/{id}")
  public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
    scheduleService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
