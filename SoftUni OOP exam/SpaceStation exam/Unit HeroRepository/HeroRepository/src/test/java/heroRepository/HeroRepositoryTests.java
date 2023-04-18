package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository

    private Hero hero;
    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        hero = new Hero("test", 5);
        heroRepository = new HeroRepository();
    }

    @Test
    public void testCreateHero() {
        heroRepository.create(hero);
        Assert.assertEquals(heroRepository.getHero("test"), hero);
    }

    @Test(expected = NullPointerException.class)
    public void testHeroWithNull() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHereTwice() {
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void testGetCount() {
        heroRepository.create(hero);
        int count = heroRepository.getCount();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testRemove() {
        heroRepository.create(hero);
        Assert.assertTrue(heroRepository.remove("test"));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithWhiteSpace() {
        heroRepository.remove("     ");
    }

    @Test
    public void testGetHeroWithHighestLevel(){
        Hero hero2 = new Hero("test2", 10);
        Hero hero3 = new Hero("test3", 45);
        Hero hero4 = new Hero("test4", 3);
        Hero hero5 = new Hero("test5", 22);
        heroRepository.create(hero);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        heroRepository.create(hero4);
        heroRepository.create(hero5);
        Assert.assertEquals(heroRepository.getHeroWithHighestLevel(), hero3);

    }

    @Test
    public void testGetHero(){
        Hero hero2 = new Hero("test2", 10);
        Hero hero3 = new Hero("test3", 45);
        Hero hero4 = new Hero("test4", 3);
        Hero hero5 = new Hero("test5", 22);
        heroRepository.create(hero);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        heroRepository.create(hero4);
        heroRepository.create(hero5);

        Collection<Hero> heroes = heroRepository.getHeroes();
        Assert.assertEquals(5, heroes.size());
    }


}
