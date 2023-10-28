package com.example.common.controller;

import com.example.common.model.RequestUser;
import com.example.common.service.okhttp.OkHttpService;
import com.example.common.utils.GsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/okhttp")
@Slf4j
public class OkHttpController {
    @Autowired
    private OkHttpService service;

    @GetMapping()
    public ResponseEntity<JsonNode> getData() {
        log.info("\n---------------------------------------------------------------------------&gt;\n");
        log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
        return service.getData();
    }

    @PostMapping
    public ResponseEntity<JsonNode> post(@RequestBody RequestUser request) {
        log.info("\n---------------------------------------------------------------------------&gt;\n");
        log.info("[Post] {}", ServletUriComponentsBuilder.fromCurrentRequest().build());
        JsonNode response = service.post(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/async")
    public String postAsync(@RequestBody RequestUser request) throws Exception {
        log.info("\n---------------------------------------------------------------------------&gt;\n");
        log.info("[Post] {}", ServletUriComponentsBuilder.fromCurrentRequest().build());
        return service.postAsync(request);
    }
}

