package com.example.metting_planner.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto {

    private int id;

    private String name;

    private Set<RoomDto> rooms;

}
