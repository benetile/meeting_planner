package com.example.metting_planner.controllers;

import com.example.metting_planner.dtos.RoomDto;
import com.example.metting_planner.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<List<RoomDto>> showAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
}
