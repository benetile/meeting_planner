package com.example.metting_planner.services;

import com.example.metting_planner.dtos.LevelDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LevelService {
    List<LevelDto> getAllLevels();
}
