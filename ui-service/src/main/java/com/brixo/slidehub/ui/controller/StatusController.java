package com.brixo.slidehub.ui.controller;

import com.brixo.slidehub.ui.model.StatusChecksResponse;
import com.brixo.slidehub.ui.service.StatusChecksService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {

    private final StatusChecksService statusChecksService;

    @Value("${slidehub.poll.status.interval-ms:2000}")
    private int statusPollIntervalMs;

    public StatusController(StatusChecksService statusChecksService) {
        this.statusChecksService = statusChecksService;
    }

   