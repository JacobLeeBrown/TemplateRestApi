package com.jlbrown.apps.controller;

import com.jlbrown.apps.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateRestControllerTest {

    private TimeService timeServiceMock;

    private TemplateRestController target;

    @BeforeEach
    public void setUp() {
        timeServiceMock = Mockito.mock(TimeService.class);
        target = new TemplateRestController(timeServiceMock);
    }

    @Test
    public void testHelloHappyPath() {
        String testInput = "Spring Boot";

        String actual = target.hello(testInput);

        String expected = "Hello, Spring Boot!";

        assertEquals(expected, actual);
    }

}