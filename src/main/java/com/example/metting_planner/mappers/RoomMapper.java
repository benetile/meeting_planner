package com.example.metting_planner.mappers;

import com.example.metting_planner.dtos.RoomDto;
import com.example.metting_planner.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toRoom(RoomDto roomDto);

    @Mapping(target = "level.rooms", ignore = true)
    RoomDto toRoomDto(Room room);
}
