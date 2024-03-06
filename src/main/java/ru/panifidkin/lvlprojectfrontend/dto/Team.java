package ru.panifidkin.lvlprojectfrontend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Team {
    private String name;
    private String founder;
    private String rating;
    private String status;
    private LocalDate birthday;
}
