package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.entity.Language;
import com.turkcell.playcell.cms.cmsdemo1.service.LanguageService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getLanguages() {
        logger.info("Retrieving languages.");
        JSONArray jsonArray = new JSONArray();
        if (!languageService.retrieveLanguages().isEmpty()) {
            List<Language> languageList = languageService.retrieveLanguages();
            for (Language language : languageList) {
                jsonArray.put(feedJson(language));
            }
            logger.info("Languages listed");
        }
        return new ResponseEntity<Object>(jsonArray.toString(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addLanguage(@RequestBody Language language) {
        logger.info("Adding a language.");
        try {
            languageService.saveLanguage(language);
            return new ResponseEntity<Object>(feedJson(language).toMap(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{languageId]")
    public ResponseEntity<Object> getLanguage(@PathVariable(name = "languageId")Long id) {
        logger.info("Retrieving a language.");
        if (languageService.existsById(id)) {
            Language language = languageService.retrieveLanguage(id);
            JSONObject json = new JSONObject();
            json.put("id", language.getId());
            json.put("name", language.getName());
            json.put("shortName", language.getShortName());
            return new ResponseEntity<Object>(json.toMap(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Object> deleteLanguage(@PathVariable(name = "languageId")Long id) {
        logger.info("Deleting a game.");
        if (languageService.existsById(id)) {
            languageService.deleteLanguage(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{languageId}")
    public ResponseEntity updateLanguage(@RequestBody Language newLanguage, @PathVariable(name = "languageId") Long id) {
        logger.info("Updating a game.");
        if (languageService.existsById(id)) {
            Language oldLanguage = languageService.retrieveLanguage(id);
            if (newLanguage.getName() != null)
                oldLanguage.setName(newLanguage.getName());
            if (newLanguage.getShortName() != null)
                oldLanguage.setShortName(newLanguage.getShortName());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public JSONObject feedJson(Language language) {
        JSONObject json = new JSONObject();
        json.put("id", language.getId());
        json.put("name", language.getName());
        json.put("shortName", language.getShortName());
        return json;
    }
}
