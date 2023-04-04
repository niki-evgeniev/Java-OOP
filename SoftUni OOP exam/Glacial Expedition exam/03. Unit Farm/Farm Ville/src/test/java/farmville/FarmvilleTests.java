package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {

    private Animal chicken;
    private Animal cow;
    private Animal donkey;
    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        chicken = new Animal("chicken", 55);
        cow = new Animal("cow", 55);
        donkey = new Animal("donkey", 55);
        animal = new Animal("animal", 55);
        farm = new Farm("Farm", 3);
    }

    @Test
    public void testConstructor() {
        farm.add(chicken);
        farm.add(cow);
        farm.add(donkey);
        Assert.assertEquals(farm.getCount(), 3);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNull() {
        Farm farmTest = new Farm(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithWhiteSpace() {
        Farm farmTest = new Farm("      ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeNumber() {
        Farm farmTest = new Farm("test", -5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testExistAnimal(){
        farm.add(chicken);
        farm.add(chicken);
    }
    @Test (expected =  IllegalArgumentException.class)
    public void testAddAnimalWithMoreCapacity(){
        farm.add(chicken);
        farm.add(cow);
        farm.add(donkey);
        farm.add(animal);
    }
    @Test
    public void testRemoveAnimal(){
        farm.add(chicken);
        Assert.assertTrue(farm.remove("chicken"));
    }
    @Test
    public void testRemoveAnimalWithNoExistAnimal(){
        farm.add(chicken);
        Assert.assertFalse(farm.remove("cow"));
    }
    @Test
    public void testGetter(){
        farm.add(chicken);
        Assert.assertEquals(farm.getName(),"Farm" );
    }


}
