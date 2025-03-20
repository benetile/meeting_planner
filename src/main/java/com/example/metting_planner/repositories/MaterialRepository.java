package com.example.metting_planner.repositories;

import com.example.metting_planner.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    Material findByNameAndQuantityIsNotNull(String name);
}
