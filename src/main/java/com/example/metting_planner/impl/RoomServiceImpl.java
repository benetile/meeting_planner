package com.example.metting_planner.impl;

import com.example.metting_planner.dtos.RoomDto;
import com.example.metting_planner.filters.RoomSpecification;
import com.example.metting_planner.mappers.RoomMapper;
import com.example.metting_planner.models.Material;
import com.example.metting_planner.models.Meeting;
import com.example.metting_planner.models.Room;
import com.example.metting_planner.repositories.MaterialRepository;
import com.example.metting_planner.repositories.RoomRepository;
import com.example.metting_planner.services.RoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private MaterialRepository materialRepository;


    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toRoomDto).toList();
    }

    @Override
    public Room attributeMeetingRoom(Meeting meeting) {
        Room suitableRoom = null;
        log.info("Meeting room attribute");

        Specification<Room> spec = RoomSpecification.roomSpecification(meeting);
        List<Room> rooms = roomRepository.findAll(spec);
        suitableRoom = getRoom(meeting,  rooms);

        return suitableRoom;
    }

    private Room getRoom(Meeting meeting, List<Room> rooms) {
        Room suitableRoom = new Room();
        switch (meeting.getType()) {
            case VC:
                suitableRoom = checkIfMeetingVCOrRC(rooms, "Ecran", "Pieuvre", "Webcam", meeting);
                break;
            case SPEC:
                suitableRoom = checkIfMeetingSPEC(meeting, rooms);
                break;
            case RS:
                suitableRoom = checkIfMeetingRS(meeting, rooms);
                break;
            case RC:
                suitableRoom = checkIfMeetingVCOrRC(rooms, "Tableau", "Ecran", "Pieuvre", meeting);
                break;
        }

        if (suitableRoom == null || Objects.isNull(suitableRoom)) {
            suitableRoom = findRoomWithMobileEquipment(meeting, rooms);
        }

        return suitableRoom;
    }

    private static Room checkIfMeetingRS(Meeting meeting, List<Room> rooms) {
        return rooms.stream()
                .filter(room -> room.getCapacity() > 3
                        && room.getCapacity() >= meeting.getCapacity())
                .findFirst()
                .orElse(null);
    }

    private static Room checkIfMeetingSPEC(Meeting meeting, List<Room> rooms) {
        return rooms.stream()
                .filter(room -> room.getEquipment().contains("Tableau")
                        && room.getCapacity() >= meeting.getCapacity())
                .findFirst()
                .orElse(null);
    }

    private static Room checkIfMeetingVCOrRC(List<Room> rooms, String écran, String pieuvre, String webcam, Meeting meeting) {
        return rooms.stream()
                .filter(room -> room.getEquipment().contains(écran)
                        && room.getEquipment().contains(pieuvre)
                        && room.getEquipment().contains(webcam))
                .findFirst()
                .orElse(null);
    }

    private List<String> getMissingEquipment(Room room, Meeting meeting) {
        List<String> requiredEquipment = switch (meeting.getType()) {
            case VC -> List.of("Ecran", "Pieuvre", "Webcam");
            case SPEC -> List.of("Tableau");
            case RS -> List.of();
            case RC -> List.of("Tableau", "Ecran", "Pieuvre");
        };

        return requiredEquipment.stream()
                .filter(e -> !room.getEquipment().contains(e))
                .toList();
    }
    private Room findRoomWithMobileEquipment(Meeting meeting, List<Room> rooms) {
        for (Room room : rooms) {
            List<String> missingEquipment = getMissingEquipment(room, meeting);
            if (missingEquipment.isEmpty() || canUseMobileEquipment(missingEquipment)) {
                reserveMobileEquipment(missingEquipment);
                return room;
            }
        }
        return null;
    }
    private boolean canUseMobileEquipment(List<String> missingEquipment) {
        for (String equipment : missingEquipment) {
            Material material = materialRepository.findByNameAndQuantityIsNotNull(equipment);
            if (material == null) {
                return false;
            }
        }
        return true;
    }

    private void reserveMobileEquipment(List<String> missingEquipment) {
        for (String equipment : missingEquipment) {
            Material material = materialRepository.findByNameAndQuantityIsNotNull(equipment);
            if (material != null && material.getQuantity() > 0) {
                material.setQuantity(material.getQuantity() - 1);
                materialRepository.save(material);
            }
        }
    }
}
