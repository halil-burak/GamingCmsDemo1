package com.hburak.cms.cmsdemo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String home() {
        logger.info("CMS API root page visited.");
        return "CMS API demo root. Under construction!";
    }
}
