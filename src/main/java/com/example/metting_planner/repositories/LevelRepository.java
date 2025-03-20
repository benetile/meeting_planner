package com.example.metting_planner.repositories;

import com.example.metting_planner.models.Level;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
