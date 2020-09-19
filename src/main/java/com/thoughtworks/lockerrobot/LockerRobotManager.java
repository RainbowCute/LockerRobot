package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.exception.TicketInvalidException;
import lombok.Getter;

import java.util.List;

@Getter
public class LockerRobotManager {
    private final List<Storable> storables;

    public LockerRobotManager(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket save(Bag bag) {
        return storables.stream()
                .filter(storable -> storable.getType() == bag.getType())
                .filter(storable -> !storable.isFull())
                .findFirst()
                .map(storable -> storable.save(bag))
                .orElse(null);
    }

    public Bag take(Ticket ticket) {
        return storables.stream()
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }
}
