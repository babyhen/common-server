package com.pawpaw.commonserver.controller;

import com.pawpaw.commonserver.service.ISMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController implements ISMSController {

    private final Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    private ISMSService service;

    @Override
    public boolean sendRegistryCode(String mobile, String code) {

        this.service.sendRegistCode(mobile, code);
        return true;
    }
}
