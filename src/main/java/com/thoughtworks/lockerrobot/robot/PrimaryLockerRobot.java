package com.thoughtworks.lockerrobot.robot;

import com.thoughtworks.lockerrobot.Locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends BaseLockerRobot {
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream().filter(locker -> !locker.isFull()).findFirst();
    }
}
