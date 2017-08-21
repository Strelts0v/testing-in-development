package com.vg.server.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String HERO_NAME_JSON_PATH = "$.name";

    private static final String HERO_ARRAY_SIZE_JSON_PATH = "$";

    private static final String DEFAULT_HERO_NAME = "EMPTY";

    private static final String HERO_ID_PARAM = "id";

    private static final String HEROES_COUNT_PARAM = "count";

    @Test
    public void noParamHeroShouldReturnDefaultHero() throws Exception {
        final String urlTemplate = "/api/hero";

        this.mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(jsonPath(HERO_ARRAY_SIZE_JSON_PATH, isEmptyString()));
    }

    @Test
    public void paramHeroShouldReturnExistHero() throws Exception {
        final String urlTemplate = "/api/hero";
        final String idParam = "11";
        final String expectedHeroName = "Dynama";

        this.mockMvc.perform(get(urlTemplate).param(HERO_ID_PARAM, idParam))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(HERO_NAME_JSON_PATH).value(expectedHeroName));
    }

    @Test
    public void illegalParamHeroShouldReturnDefaultHero() throws Exception {
        final String urlTemplate = "/api/hero";
        final String idParam = "illegal";

        this.mockMvc.perform(get(urlTemplate).param(HERO_ID_PARAM, idParam))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(HERO_NAME_JSON_PATH).value(DEFAULT_HERO_NAME));
    }

    @Test
    public void paramHeroesShouldReturnCorrespondHeroesCount() throws Exception {
        final String urlTemplate = "/api/heroes";
        final String countParam = "7";
        final int expectedHeroCount = 7;

        this.mockMvc.perform(get(urlTemplate).param(HEROES_COUNT_PARAM, countParam))
                .andExpect(status().isOk())
                .andExpect(jsonPath(HERO_ARRAY_SIZE_JSON_PATH, hasSize(expectedHeroCount)));
    }
}
