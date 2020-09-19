package com.thoughtworks.lockerrobot.robot;

import com.thoughtworks.lockerrobot.Bag;
import com.thoughtworks.lockerrobot.Locker;
import com.thoughtworks.lockerrobot.Ticket;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot extends BaseLockerRobot{
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream()
                .filter(locker -> !locker.isFull())
                .max(Comparator.comparingDouble(Locker::getFreeCapacityRate));
    }
}
