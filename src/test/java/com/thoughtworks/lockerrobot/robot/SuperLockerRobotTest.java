package com.thoughtworks.lockerrobot.robot;

import com.thoughtworks.lockerrobot.Bag;
import com.thoughtworks.lockerrobot.Locker;
import com.thoughtworks.lockerrobot.Ticket;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class SuperLockerRobotTest {

    @Test
    public void should_return_ticket_when_super_locker_robot_save_bag_given_a_locker_with_free_capacity_and_bag() {
        Locker locker = new Locker(10);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(locker));
        Bag bag = new Bag();

        Ticket ticket = superLockerRobot.save(bag);

        assertNotNull(ticket);
    }
}
