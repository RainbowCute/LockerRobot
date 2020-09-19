package com.thoughtworks.lockerrobot;

public interface Storable {

    Ticket save(Bag bag);

    Bag take(Ticket ticket);

    boolean isFull();

    boolean isExist(Ticket ticket);

}
