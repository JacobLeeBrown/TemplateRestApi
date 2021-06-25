package com.jlbrown.apps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlbrown.apps.models.DialogflowRequest;
import com.jlbrown.apps.models.DialogflowResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;

@RestController
public class DialogflowController {

    private static final Logger log = LoggerFactory.getLogger(DialogflowController.class);

    private static final String HELLO_TEMPLATE = "Hello, %s!";

    private static final String ADD_ITEM = "item.add";
    private static final String REMOVE_ITEM = "item.remove";
    private static final String LIST_ITEMS = "item.list";

    private final HashSet<String> myItems = new HashSet<>();

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "World") String name
    ) {
        return String.format(HELLO_TEMPLATE, name);
    }

    @PostMapping(name = "/")
    public DialogflowResponse dialogflowBase(@RequestBody String body) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        DialogflowRequest request = mapper.readValue(body, DialogflowRequest.class);

        String action = request.getResult().getAction();
        String item = request.getResult().getParameters().getItem();

        if (ADD_ITEM.equals(action)) {
            myItems.add(item);
        } else if (REMOVE_ITEM.equals(action)) {
            myItems.remove(item);
        } else if (LIST_ITEMS.equals(action)) {
            item = myItems.toString();
        }

        return new DialogflowResponse("SUCCESS", String.format("Action: %s, Item(s): %s", action, item));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Encountered error: ", e);
        DialogflowResponse responseBody = new DialogflowResponse("FAILURE", String.format("Encountered Exception - %s - %s", e.getClass().toString(), e.getMessage()));
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
