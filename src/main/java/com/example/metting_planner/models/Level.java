package com.example.metting_planner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private int id;

    private String name;

    @OneToMany(mappedBy = "level",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Room> rooms;
}
