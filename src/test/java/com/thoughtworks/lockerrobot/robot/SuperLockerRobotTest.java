package com.thoughtworks.lockerrobot.robot;

import com.thoughtworks.lockerrobot.Bag;
import com.thoughtworks.lockerrobot.Locker;
import com.thoughtworks.lockerrobot.Ticket;
import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.exception.FullCapacityException;
import com.thoughtworks.lockerrobot.exception.TicketInvalidException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperLockerRobotTest {

    @Test
    public void should_return_ticket_when_super_locker_robot_save_bag_given_a_locker_with_free_capacity_and_bag() {
        Locker locker = new Locker(10, Type.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(locker));
        Bag bag = new Bag();

        Ticket ticket = superLockerRobot.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_and_save_bag_in_1st_locker_when_super_locker_robot_save_bag_given_1st_locker_with_more_free_capacity_rate_and_bag() {
        Locker firstLocker = new Locker(10, Type.L);
        Locker secondLocker = new Locker(5, Type.L);
        secondLocker.save(new Bag());
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(Type.L);

        Ticket ticket = superLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_locker_when_super_locker_robot_save_bag_given_2nd_locker_more_free_capacity_rate_and_bag() {
        Locker firstLocker = new Locker(20, Type.L);
        Locker secondLocker = new Locker(10, Type.L);
        firstLocker.save(new Bag());
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(Type.L);

        Ticket ticket = superLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, secondLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_1st_locker_when_super_locker_robot_save_bag_given_two_lockers_with_same_free_capacity_rate_and_bag() {
        Locker firstLocker = new Locker(5, Type.L);
        Locker secondLocker = new Locker(10, Type.L);
        firstLocker.save(new Bag());
        secondLocker.save(new Bag());
        secondLocker.save(new Bag());
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag(Type.L);

        Ticket ticket = superLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_super_locker_robot_save_bag_given_two_lockers_with_0_free_capacity_and_bag() {
        Locker firstLocker = new Locker(1, Type.L);
        Locker secondLocker = new Locker(1, Type.L);
        firstLocker.save(new Bag());
        secondLocker.save(new Bag());
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        superLockerRobot.save(bag);
    }

    @Test
    public void should_take_bag_successfully_when_super_locker_robot_take_bag_given_1_locker_and_valid_ticket() {
        Locker locker = new Locker(10, Type.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(locker));
        Bag bag = new Bag(Type.L);
        Ticket ticket = superLockerRobot.save(bag);

        Bag takenBag = superLockerRobot.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_is_invalid_when_super_locker_robot_take_bag_given_a_locker_invalid_ticket() {
        Locker locker = new Locker(10, Type.L);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(locker));
        superLockerRobot.save(new Bag());

        superLockerRobot.take(new Ticket());
    }
}
