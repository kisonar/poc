package com.mossad.mmigdal.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MossadSpringWebRestService {

    @RequestMapping("/status")
    public String status() {
        return "Spring Web Service status is up & running ...";
    }

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "Spring Web Service helth check...";
    }

}
