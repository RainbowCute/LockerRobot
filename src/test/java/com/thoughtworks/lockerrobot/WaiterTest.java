package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import com.thoughtworks.lockerrobot.robot.PrimaryLockerRobot;
import com.thoughtworks.lockerrobot.robot.SuperLockerRobot;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WaiterTest {

    @Test
    public void should_return_ticket_and_bag_in_S_type_locker_when_waiter_save_bag_given_3_types_of_locker_and_S_type_bag_and_common_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        Waiter waiter = new Waiter(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.S);

        Ticket ticket = waiter.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, locker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_M_type_locker_when_waiter_save_bag_given_3_types_of_locker_and_M_type_bag_and_common_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        Waiter waiter = new Waiter(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.M);

        Ticket ticket = waiter.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, primaryLockerRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_L_type_locker_when_waiter_save_bag_given_3_types_of_locker_and_L_type_bag_and_common_customer() {
        Locker locker = new Locker(10, Type.S);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10, Type.M)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(Collections.singletonList(new Locker(10, Type.L)));
        Waiter waiter = new Waiter(Arrays.asList(locker, primaryLockerRobot, superLockerRobot));
        Bag bag = new Bag(Type.L);

        Ticket ticket = waiter.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, superLockerRobot.take(ticket));
    }
}
