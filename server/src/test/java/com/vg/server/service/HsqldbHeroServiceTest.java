package com.vg.server.service;

import com.vg.server.model.Hero;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// set Spring test runner instead of JUnit
@RunWith(SpringRunner.class)
// specify that our application is based on Spring Boot
@SpringBootTest
// set auto configuration for used classes
@AutoConfigureMockMvc
public class HsqldbHeroServiceTest {

    @Autowired
    private HeroService service;

    @Test
    public void findHeroByIdTShouldReturnHeroWithCorrespondNameTest()
            throws Exception {
        final long heroId = 2L;
        final String expectedHeroName = "Superman";
        final String errorMessage =
                "Expected hero name and actual are different";

        Hero actualHero = service.findHeroById(heroId);
        Assert.assertEquals(errorMessage,
                expectedHeroName, actualHero.getName());
    }

    @Test
    public void findHeroesByNameShouldReturnCorrespondHeroListSizeTest()
            throws Exception {
        final String heroName = "Superman";
        final int expectedHeroListSize = 1;
        final String errorMessage =
                "Expected hero list size and actual are different";

        List<Hero> actualHeroList = service.findHeroesByName(heroName);
        Assert.assertEquals(errorMessage,
                expectedHeroListSize, actualHeroList.size());
    }

    @Test
    public void getHeroesLimitedShouldReturnCorrespondHeroListSizeTest()
            throws Exception {
        final int expectedHeroListSize = 5;
        final String errorMessage =
                "Expected hero list size and actual are different";

        List<Hero> actualHeroList = service
                .getHeroesLimited(expectedHeroListSize);
        Assert.assertEquals(errorMessage,
                expectedHeroListSize, actualHeroList.size());
    }

    @Test
    public void addHeroShouldReturnHeroWithNonZeroIdTest()
            throws Exception {
        final String heroName = "Test";
        Hero hero = new Hero();
        hero.setName(heroName);

        final long notExpectedHeroId = 0L;
        final String errorMessage =
                "Expected not 0 generated id in added hero";
        // expect that generated hero id is not 0
        Hero addedHero = service.addHero(hero);
        Assert.assertNotEquals(errorMessage,
                notExpectedHeroId, addedHero.getId());

        // after successful test delete added hero
        service.deleteHero(addedHero.getId());
    }

    @Test
    public void updateHeroShouldCorrespondCountOfUpdatedRowsTest()
            throws Exception {
        final String heroName = "Test";
        final String updatedHeroName = "Updated Test";
        Hero hero = new Hero();
        hero.setName(heroName);

        // expect that generated hero id is not 0
        Hero addedHero = service.addHero(hero);
        addedHero.setName(updatedHeroName);
        int updatedRows = service
                .updateHero(addedHero.getId(), addedHero);

        final int expectedUpdatedRows = 1;
        final String errorMessage =
                "Expected " + expectedUpdatedRows +
                        " generated id in added hero";

        Assert.assertEquals(errorMessage,
                expectedUpdatedRows, updatedRows);

        // after successful test delete added hero
        service.deleteHero(addedHero.getId());
    }
}