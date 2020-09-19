package com.thoughtworks.lockerrobot;

import java.util.List;

public class Waiter {
    private final List<Storable> storables;

    public Waiter(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket save(Bag bag) {
        return storables.stream()
                .filter(storable -> storable.getType() == bag.getType())
                .findFirst()
                .map(storable -> storable.save(bag))
                .orElse(null);
    }
}
