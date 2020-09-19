package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.exception.FullCapacityException;
import com.thoughtworks.lockerrobot.exception.TicketInvalidException;
import com.thoughtworks.lockerrobot.exception.TypeNotMatchException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_ticket_when_save_bag_given_locker_with_10_free_capacity() {
        Locker locker = new Locker(10, Type.S);
        Bag bag = new Bag(Type.S);

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_capacity_is_full_exception_when_save_bag_given_locker_with_0_free_capacity() {
        Locker locker = new Locker(1, Type.S);
        locker.save(new Bag());

        locker.save(new Bag());
    }

    @Test
    public void should_take_bag_successfully_when_take_bag_given_locker_with_10_capacity_and_valid_ticket() {
        Locker locker = new Locker(10, Type.S);
        Bag bag = new Bag(Type.S);
        Ticket ticket = locker.save(bag);

        Bag takenBag = locker.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_is_illegal_exception_when_take_bag_given_locker_and_illegal_ticket() {
        Locker locker = new Locker(10, Type.S);

        locker.take(new Ticket());

    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_locker_and_reused_ticket() {
        Locker locker = new Locker(1, Type.S);
        Bag bag = new Bag(Type.S);
        Ticket ticket = locker.save(bag);
        locker.take(ticket);

        locker.take(ticket);
    }

    @Test
    public void should_return_ticket_when_save_bag_given_a_full_locker_then_take_bag_and_new_bag() {
        Locker locker = new Locker(1, Type.S);
        Ticket ticket = locker.save(new Bag(Type.S));
        locker.take(ticket);
        Bag bag = new Bag(Type.S);

        Ticket otherTicket = locker.save(bag);

        assertNotNull(otherTicket);
    }

    @Test(expected = TypeNotMatchException.class)
    public void should_throw_Type_not_match_exception_when_locker_take_bag_given_not_match_ticket() {
        Locker locker1 = new Locker(10, Type.S);
        Locker locker2 = new Locker(10, Type.M);
        Ticket ticket1 = locker1.save(new Bag(Type.S));
        locker2.save(new Bag(Type.M));

        locker2.take(ticket1);
    }

    @Test(expected = TypeNotMatchException.class)
    public void should_throw_Type_not_match_exception_when_locker_save_bag_given_not_match_bag() {
        Locker locker = new Locker(10, Type.S);

        locker.save(new Bag(Type.M));
    }
}
