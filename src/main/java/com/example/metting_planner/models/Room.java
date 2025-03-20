package com.example.metting_planner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;

    private int capacity;

    private String equipment;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Meeting> meetings;
}
