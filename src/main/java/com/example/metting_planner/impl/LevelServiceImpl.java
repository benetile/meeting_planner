package com.example.metting_planner.impl;

import com.example.metting_planner.dtos.LevelDto;
import com.example.metting_planner.mappers.LevelMapper;
import com.example.metting_planner.repositories.LevelRepository;
import com.example.metting_planner.services.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private LevelMapper levelMapper;

    @Override
    public List<LevelDto> getAllLevels() {
        return levelRepository.findAll().stream()
                .map(levelMapper::toLevelDto).toList();
    }
}
