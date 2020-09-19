package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.exception.FullCapacityException;
import com.thoughtworks.lockerrobot.robot.PrimaryLockerRobot;
import com.thoughtworks.lockerrobot.robot.SuperLockerRobot;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerRobotManagerTest {

    @Test
    public void should_return_ticket_and_bag_in_S_type_locker_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_S_type_bag_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.S);

        Ticket ticket = lockerRobotManager.save(bag);

        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(bag, locker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_M_type_locker_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_M_type_bag_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.M);

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, primaryLockerRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_L_type_locker_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_L_type_bag_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.L);

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, superLockerRobot.take(ticket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_and_S_type_locker_has_no_free_capacity_and_S_type_bag_and_VIP_customer() {
        Locker locker = new Locker(1, Type.S);
        locker.save(new Bag());
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.S);

        lockerRobotManager.save(bag);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_and_M_type_locker_has_no_free_capacity_and_M_type_bag_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1, Type.M)));
        primaryLockerRobot.save(new Bag());
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.M);

        lockerRobotManager.save(bag);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_locker_robot_manager_save_bag_given_3_types_of_locker_and_and_L_type_locker_has_no_free_capacity_and_L_type_bag_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(1, Type.L)));
        superLockerRobot.save(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.L);

        lockerRobotManager.save(bag);
    }

    @Test
    public void should_return_correct_bag_when_locker_robot_manager_take_bag_given_3_types_of_storables_and_valid_ticket_and_VIP_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.L);
        Ticket ticket = lockerRobotManager.save(bag);

        Bag takenBag = lockerRobotManager.take(ticket);

        assertNotNull(takenBag);
        assertEquals(bag, takenBag);
    }
}
