package com.example.metting_planner.dtos;

import com.example.metting_planner.models.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int capacity;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private RoomDto room;

}
