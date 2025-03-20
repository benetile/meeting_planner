package com.example.metting_planner.services;

import com.example.metting_planner.dtos.RoomDto;
import com.example.metting_planner.models.Meeting;
import com.example.metting_planner.models.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomService {

    List<RoomDto> getAllRooms();

    Room attributeMeetingRoom(Meeting meeting);
}
