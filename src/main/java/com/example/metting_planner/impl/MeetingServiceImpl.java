package com.example.metting_planner.impl;

import com.example.metting_planner.dtos.MeetingDto;
import com.example.metting_planner.mappers.MeetingMapper;
import com.example.metting_planner.models.Meeting;
import com.example.metting_planner.models.Room;
import com.example.metting_planner.repositories.MeetingRepository;
import com.example.metting_planner.services.MeetingService;
import com.example.metting_planner.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MeetingServiceImpl implements MeetingService {
    private static final Logger log = LoggerFactory.getLogger(MeetingServiceImpl.class);

    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private RoomService roomService;


    @Override
    public List<MeetingDto> getMeetings() {
        return meetingRepository.findAll().stream()
                .map(meetingMapper::toMeetingDto).toList();
    }

    @Override
    public List<MeetingDto> initMeetingARoom() {
        log.info("Starting attribute meeting a room");
        List<Meeting> meetings = meetingRepository.findAll();

        meetings.stream().filter(Objects::nonNull)
                .forEach(meeting -> {
                    Room room = roomService.attributeMeetingRoom(meeting);
                    meeting.setRoom(room);
                    meetingRepository.save(meeting);
                });
        return meetings.stream().map(meetingMapper::toMeetingDto).toList();
    }
}
