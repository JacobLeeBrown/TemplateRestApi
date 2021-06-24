package com.jlbrown.apps.controller;

import com.jlbrown.apps.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jlbrownapps")
public class TemplateRestController {

    private static final String HELLO_TEMPLATE = "Hello, %s!";

    private final TimeService timeService;

    public TemplateRestController(@Autowired TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "World") String name
    ) {
        return String.format(HELLO_TEMPLATE, name);
    }

    @GetMapping("/time")
    public String timeDefault() {
        return timeService.getCurrentTime(TimeService.SYSTEM_ZONE.toString());
    }

    @GetMapping("/time/currentTime")
    public String timeGetCurrentTime(
            @RequestParam(value = "zone", defaultValue = "UTC") String zone
    ) {
        return timeService.getCurrentTime(zone);
    }

}
