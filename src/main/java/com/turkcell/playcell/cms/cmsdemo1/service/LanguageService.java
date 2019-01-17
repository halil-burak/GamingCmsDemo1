package com.turkcell.playcell.cms.cmsdemo1.service;

import com.turkcell.playcell.cms.cmsdemo1.entity.Language;

import java.util.List;

public interface LanguageService {

    public List<Language> retrieveLanguages();

    public void saveLanguage(Language language);

    public Language retrieveLanguage(Long id);

    public void deleteLanguage(Long id);

    public boolean existsById(Long id);
}
