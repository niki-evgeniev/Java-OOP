package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private Cat cat;
    private House house;

    @Before
    public void setup() {
        cat = new Cat("test");
        house = new House("houseName", 1);
    }

    @Test
    public void testAddCat() {
        house.addCat(cat);
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatWithMoreCapacity() {
        house.addCat(cat);
        Cat cat1 = new Cat("test1");
        house.addCat(cat1);
    }

    @Test(expected = NullPointerException.class)
    public void testHouseWithNull() {
        House house1 = new House(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHouseWithInvalidCapacity() {
        House house1 = new House("name", -5);
    }

    @Test
    public void testRemoveCat() {
        house.addCat(cat);
        house.removeCat("test");
        Assert.assertEquals( 0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatWithNull() {
//        house.removeCat(null);
//        Assert.assertEquals(0, house.getCount() );
        house.removeCat("Jorko");
    }
    @Test
    public void testCatForSale(){
        house.addCat(cat);
        Assert.assertEquals(cat, house.catForSale("test"));
        Assert.assertFalse(cat.isHungry());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testCatForSaleWithNull(){
        Assert.assertEquals(cat, house.catForSale("test"));
    }
    @Test
    public void testStatistics(){
        house.addCat(cat);
        Assert.assertEquals("The cat test is in the house houseName!",
                house.statistics());
    }
}
