package com.turkcell.playcell.cms.cmsdemo1.service;

import com.turkcell.playcell.cms.cmsdemo1.entity.Platform;

import java.util.List;

public interface PlatformService {

    public List<Platform> retrievePlatforms();

    public void savePlatform(Platform language);

    public Platform retrievePlatform(Long id);

    public void deletePlatform(Long id);
}
