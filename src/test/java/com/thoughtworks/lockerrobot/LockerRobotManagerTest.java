package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.exception.FullCapacityException;
import com.thoughtworks.lockerrobot.exception.TicketInvalidException;
import com.thoughtworks.lockerrobot.robot.BaseLockerRobot;
import com.thoughtworks.lockerrobot.robot.PrimaryLockerRobot;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_and_save_bag_in_1st_robot_when_locker_robot_manager_save_bag_given_2_robot_exist_free_capacity_and_2_locker_exist_free_capacity() {
        Locker firstLocker = new Locker(10, Type.S);
        Locker secondLocker = new Locker(10, Type.S);
        BaseLockerRobot firstRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        BaseLockerRobot secondRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(firstRobot, secondRobot));
        Bag bag = new Bag(Type.M);

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_when_locker_robot_manager_save_bag_given_1_robot_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null, Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_when_locker_robot_manager_save_bag_given_1_locker_with_free_capacity_and_bag() {
        Locker locker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), null);
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_robot_when_locker_robot_manager_save_bag_given_1st_robot_with_full_capacity_and_2nd_robot_with_free_capacity_and_2_locker_with_free_capacity_and_bag() {
        BaseLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        PrimaryLockerRobot smartLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        Locker firstLocker = new Locker(10, Type.S);
        Locker secondLocker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag(Type.M);
        primaryLockerRobot.save(new Bag());

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, smartLockerRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_1st_locker_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_1st_locker_with_free_capacity_and_2nd_locker_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        PrimaryLockerRobot smartLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(10, Type.S);
        Locker secondLocker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag(Type.S);

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_locker_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_1st_locker_with_full_capacity_and_2nd_locker_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        PrimaryLockerRobot smartLockerRobot = new PrimaryLockerRobot((Collections.singletonList(new Locker(1, Type.M))));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(1, Type.S);
        firstLocker.save(new Bag());
        Locker secondLocker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag(Type.S);

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, secondLocker.take(ticket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_2_locker_with_full_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        PrimaryLockerRobot smartLockerRobot = new PrimaryLockerRobot((Collections.singletonList(new Locker(1, Type.M))));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(1, Type.S);
        firstLocker.save(new Bag());
        Locker secondLocker = new Locker(1, Type.S);
        secondLocker.save(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();

        lockerRobotManager.save(bag);
    }

    @Test
    public void should_return_bag_successfully_when_locker_robot_manager_take_bag_given_1_robot_and_1_locker_and_valid_ticket() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        Locker locker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag(Type.M);
        Ticket ticket = lockerRobotManager.save(bag);

        Bag takenBag = lockerRobotManager.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_locker_robot_manager_take_bag_given_1_robot_and_1_locker_and_invalid_ticket() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        Locker locker = new Locker(10, Type.S);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag(Type.M);
        lockerRobotManager.save(bag);
        Ticket invalidTicket = new Ticket();

        lockerRobotManager.take(invalidTicket);
    }
}
