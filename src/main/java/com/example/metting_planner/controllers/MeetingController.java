package com.example.metting_planner.controllers;

import com.example.metting_planner.dtos.MeetingDto;
import com.example.metting_planner.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/")
    public ResponseEntity<List<MeetingDto>> showAllMeetings() {
        return ResponseEntity.ok(meetingService.getMeetings());
    }

    @GetMapping("/init")
    public ResponseEntity<List<MeetingDto>> initMeetings() {
        return ResponseEntity.ok(meetingService.initMeetingARoom());
    }
}
