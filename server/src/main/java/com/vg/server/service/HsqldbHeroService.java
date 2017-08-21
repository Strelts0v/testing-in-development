package com.vg.server.service;

import com.vg.server.model.Hero;
import com.vg.server.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HsqldbHeroService implements HeroService {

    @Autowired
    private HeroRepository repository;

    @Override
    public Hero findHeroById(long id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<Hero> findHeroesByName(String namePattern) {
        return repository.findByName(namePattern);
    }

    @Override
    public Iterable<Hero> getHeroes(int count) {
        return repository.getHeroes(count);
    }

    @Override
    public Iterable<Hero> getAllHeroes() {
        return repository.findAll();
    }
}
