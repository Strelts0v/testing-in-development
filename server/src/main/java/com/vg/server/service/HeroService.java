package com.vg.server.service;

import com.vg.server.model.Hero;
import org.springframework.stereotype.Service;

@Service
public interface HeroService {

    Hero findHeroById(long id);

    Iterable<Hero> findHeroesByName(String namePattern);

    Iterable<Hero> getHeroes(int count);

    Iterable<Hero> getAllHeroes();
}
