package ru.ho4upizzu.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(method = RequestMethod.GET)
public class TimeController {

    @RequestMapping("/now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
