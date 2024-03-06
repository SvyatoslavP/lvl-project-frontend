package ru.panifidkin.lvlprojectfrontend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Founder {
    private String firstName;
    private String secondName;
    private String phoneNumber;
}
