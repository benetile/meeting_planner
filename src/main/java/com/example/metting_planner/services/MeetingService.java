package com.example.metting_planner.services;

import com.example.metting_planner.dtos.MeetingDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeetingService {
    List<MeetingDto> getMeetings();

    List<MeetingDto> initMeetingARoom();

}
