package com.vg.server.controller;

import com.vg.server.model.Hero;
import com.vg.server.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HeroController {

    @Autowired
    private HeroService heroService;

    private static final String DEFAULT_HERO_ID_PARAM = "0";

    private static final String DEFAULT_HEROES_COUNT_PARAM = "all";

    @RequestMapping(value = "/hero", method=RequestMethod.GET)
    public Hero getHero(@RequestParam(value="id",
            defaultValue=DEFAULT_HERO_ID_PARAM) String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
            return heroService.findHeroById(id);
        }catch (NumberFormatException nfe){
            id = Long.parseLong(DEFAULT_HERO_ID_PARAM);
            return heroService.findHeroById(id);
        }
    }

    @RequestMapping(value="/heroes", method=RequestMethod.GET)
    public Iterable<Hero> getHeroes(@RequestParam(value="count",
            defaultValue=DEFAULT_HEROES_COUNT_PARAM) String countStr){
        if(countStr.equals(DEFAULT_HEROES_COUNT_PARAM)){
            return heroService.getAllHeroes();
        }
        int count = Integer.parseInt(countStr);
        return heroService.getHeroes(count);
    }
}
