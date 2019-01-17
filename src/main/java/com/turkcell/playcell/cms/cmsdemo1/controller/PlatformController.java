package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.entity.Platform;
import com.turkcell.playcell.cms.cmsdemo1.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public class PlatformController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlatformService platformService;

    public PlatformService getPlatformService() {
        return platformService;
    }

    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("")
    public List<Platform> getPlatforms() {
        List<Platform> platformList = platformService.retrievePlatforms();
        logger.info("Retrieving platforms.");
        return platformList;
    }

    @PostMapping("/")
    public void addPlatform(Platform platform) {
        logger.info("Adding a platform.");
        platformService.savePlatform(platform);
    }

    @GetMapping("/{pId]")
    public Platform getPlatform(@PathVariable(name = "pId")Long id) {
        Platform language = platformService.retrievePlatform(id);
        logger.info("Retrieving a platform.");
        return language;
    }

    @DeleteMapping("/{pId}")
    public void deletePlatform(@PathVariable(name = "pId")Long id) {
        logger.info("Deleting a platform.");
        platformService.deletePlatform(id);
    }
}
