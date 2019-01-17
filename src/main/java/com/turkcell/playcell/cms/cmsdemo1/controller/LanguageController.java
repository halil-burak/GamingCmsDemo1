package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.entity.Language;
import com.turkcell.playcell.cms.cmsdemo1.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LanguageService languageService;

    public LanguageService getLanguageService() {
        return languageService;
    }

    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("")
    public List<Language> getLanguages() {
        List<Language> languageList = languageService.retrieveLanguages();
        logger.info("Retrieving languages.");
        return languageList;
    }

    @PostMapping("/")
    public void addLanguage(Language language) {
        logger.info("Adding a language.");
        languageService.saveLanguage(language);
    }

    @GetMapping("/{languageId]")
    public Language getLanguage(@PathVariable(name = "languageId")Long id) {
        Language language = languageService.retrieveLanguage(id);
        logger.info("Retrieving a language.");
        return language;
    }

    @DeleteMapping("/{languageId}")
    public void deleteLanguage(@PathVariable(name = "languageId")Long id) {
        logger.info("Deleting a game.");
        languageService.deleteLanguage(id);
    }
}
