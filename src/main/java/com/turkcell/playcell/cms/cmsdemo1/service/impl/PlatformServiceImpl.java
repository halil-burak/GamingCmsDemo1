package com.turkcell.playcell.cms.cmsdemo1.service.impl;

import com.turkcell.playcell.cms.cmsdemo1.entity.Platform;
import com.turkcell.playcell.cms.cmsdemo1.repo.PlatformRepository;
import com.turkcell.playcell.cms.cmsdemo1.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    public PlatformRepository getPlatformRepository() {
        return platformRepository;
    }

    public void setPlatformRepository(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    public List<Platform> retrievePlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms;
    }

    @Override
    public void savePlatform(Platform platform) {
        platformRepository.save(platform);
    }

    @Override
    public Platform retrievePlatform(Long id) {
        Platform platform = platformRepository.getOne(id);
        return platform;
    }

    @Override
    public void deletePlatform(Long id) {
        platformRepository.deleteById(id);
    }
}
