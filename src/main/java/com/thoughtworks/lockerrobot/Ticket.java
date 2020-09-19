package com.thoughtworks.lockerrobot;

import com.thoughtworks.lockerrobot.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ticket {
    private Type type;
}
