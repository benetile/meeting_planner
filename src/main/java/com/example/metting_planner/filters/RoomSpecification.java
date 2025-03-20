package com.example.metting_planner.filters;

import com.example.metting_planner.models.Meeting;
import com.example.metting_planner.models.Room;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RoomSpecification {

    private RoomSpecification(){}

    public static Specification<Room> roomSpecification(Meeting meeting){
        final String MEETING ="meetings";
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), meeting.getCapacity()));

            Join<Room, Meeting> meetingsJoin = root.join(MEETING, JoinType.LEFT);
            Predicate noMeetingAtStartTime = criteriaBuilder.notEqual(meetingsJoin.get("startTime"), meeting.getStartTime());
            Predicate noMeetings = criteriaBuilder.isNull(meetingsJoin.get("id"));

            predicates.add(criteriaBuilder.or(noMeetingAtStartTime, noMeetings));

            return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
