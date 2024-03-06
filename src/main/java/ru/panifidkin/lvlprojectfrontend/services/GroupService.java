package ru.panifidkin.lvlprojectfrontend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panifidkin.lvlprojectfrontend.dto.Group;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {

    private final RestTemplate restTemplate;

//    public List<Group> getAllGroups() {
//        restTemplate.getForEntity("http://localhost:8181/getAllGroups");
//    }
}
