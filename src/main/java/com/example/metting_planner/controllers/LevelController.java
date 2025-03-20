package com.example.metting_planner.controllers;

import com.example.metting_planner.dtos.LevelDto;
import com.example.metting_planner.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/")
    public ResponseEntity<List<LevelDto>> getLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }
}
