package com.example.metting_planner.mappers;

import com.example.metting_planner.dtos.MeetingDto;
import com.example.metting_planner.models.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeetingMapper {

    @Mapping(target = "room.meetings", ignore = true)
    Meeting toMeeting(MeetingDto meetingDto);

    @Mapping(target = "room", ignore = false)
    @Mapping(target = "room.meetings", ignore = true)
    @Mapping(target = "room.level.rooms", ignore = true)
    MeetingDto toMeetingDto(Meeting meeting);

}
