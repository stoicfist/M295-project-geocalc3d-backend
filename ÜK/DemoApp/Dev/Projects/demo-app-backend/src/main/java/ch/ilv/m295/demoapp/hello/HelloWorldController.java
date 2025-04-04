package ch.ilv.m295.demoapp.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello there!", HttpStatus.OK);
    }
}

