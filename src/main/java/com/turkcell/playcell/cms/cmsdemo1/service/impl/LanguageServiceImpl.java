package com.hburak.cms.cmsdemo1.service.impl;

import com.hburak.cms.cmsdemo1.entity.Language;
import com.hburak.cms.cmsdemo1.repo.LanguageRepository;
import com.hburak.cms.cmsdemo1.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> retrieveLanguages() {
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }

    @Override
    public void saveLanguage(Language language) {
        languageRepository.saveAndFlush(language);
    }

    @Override
    public Language retrieveLanguage(Long id) {
        Language language = languageRepository.getOne(id);
        return language;
    }

    @Override
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }
}
