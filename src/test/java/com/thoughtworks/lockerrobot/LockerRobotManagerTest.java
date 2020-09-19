package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.robot.PrimaryLockerRobot;
import com.thoughtworks.lockerrobot.robot.SuperLockerRobot;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

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
}
