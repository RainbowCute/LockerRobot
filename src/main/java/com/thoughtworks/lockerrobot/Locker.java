package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.exception.FullCapacityException;
import com.thoughtworks.lockerrobot.exception.TicketInvalidException;
import com.thoughtworks.lockerrobot.exception.TypeNotMatchException;

import java.util.HashMap;
import java.util.Map;

public class Locker implements Storable {
    private final int capacity;
    private final Type type;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int capacity, Type type) {
        this.capacity = capacity;
        this.type = type;
    }

    public Ticket save(Bag bag) {
        if (isFull()) {
            throw new FullCapacityException();
        }

        Ticket ticket = new Ticket(bag.getType());
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if(ticket.getType() != null && type != ticket.getType()) {
            throw new TypeNotMatchException();
        }
        if (!isExist(ticket)) {
            throw new TicketInvalidException();
        }

        Bag bag = ticketBagMap.get(ticket);
        ticketBagMap.remove(ticket);
        return bag;
    }

    @Override
    public boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    @Override
    public boolean isExist(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }

    @Override
    public Type getType() {
        return type;
    }

    public int getFreeCapacity() {
        return capacity - ticketBagMap.size();
    }

    public double getFreeCapacityRate() {
        return (getFreeCapacity() + 0.0) / capacity;
    }
}
