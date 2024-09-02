package com.sparta.calendar.schedule.service;

import com.sparta.calendar.schedule.entity.Schedule;
import com.sparta.calendar.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Transactional
  public Schedule createSchedule(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  // 일정 페이징 조회 서비스
  public Page<Schedule> getSchedules(int page, int size) {
    Pageable pageable = PageRequest.of(page, size); // 페이지 번호와 크기 설정
    return scheduleRepository.findAllByOrderByModifiedDateDesc(pageable);
  }

  @Transactional
  public Schedule updateSchedule(LocalDate date, Long id, Schedule schedule) {
    List<Schedule> searchSchedule = scheduleRepository.findByDate(date);
    Schedule updatedSchedule = searchSchedule.stream()
        .filter(s -> id != null && id.equals(s.getId()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("스케줄이 없습니다."));

    updatedSchedule.setTitle(schedule.getTitle());
    updatedSchedule.setContent(schedule.getContent());
    updatedSchedule.setUserName(schedule.getUserName());
    updatedSchedule.setModifiedDate(schedule.getModifiedDate());
    updatedSchedule.updateDate();

    return scheduleRepository.save(updatedSchedule);
  }

  @Transactional
  public void delete(Long id) {
    scheduleRepository.deleteById(id);
  }
}
