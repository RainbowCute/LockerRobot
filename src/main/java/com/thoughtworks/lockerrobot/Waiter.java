package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.exception.FullCapacityException;

import java.util.List;

public class Waiter {
    private final List<Storable> storables;

    public Waiter(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket save(Bag bag) {
        return storables.stream()
                .filter(storable -> storable.getType() == bag.getType())
                .filter(storable -> !storable.isFull())
                .findFirst()
                .map(storable -> storable.save(bag))
                .orElseThrow(FullCapacityException::new);
    }

    public Bag take(Ticket ticket) {
        return storables.stream()
                .filter(storable -> storable.getType() == ticket.getType())
                .filter(storable -> storable.isExist(ticket))
                .findFirst()
                .map(storable -> storable.take(ticket))
                .orElse(null);
    }
}
