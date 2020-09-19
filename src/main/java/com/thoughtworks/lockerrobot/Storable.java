package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;

public interface Storable {

    Ticket save(Bag bag);

    Bag take(Ticket ticket);

    boolean isFull();

    boolean isExist(Ticket ticket);

    Type getType();

}
