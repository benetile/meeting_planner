package com.example.metting_planner.mappers;

import com.example.metting_planner.dtos.LevelDto;
import com.example.metting_planner.models.Level;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    @Mapping(target = "rooms", ignore = true)
    LevelDto toLevelDto(Level level);

    Level toLevel(LevelDto levelDto);
}
