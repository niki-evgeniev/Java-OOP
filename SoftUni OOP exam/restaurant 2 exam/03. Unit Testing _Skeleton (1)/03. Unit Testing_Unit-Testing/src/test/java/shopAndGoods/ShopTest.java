package shopAndGoods;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ShopTest {

    Goods goods;
    Goods goods1;
    Goods goods2;
    Goods goods3;
    Shop shop;
    Shop shop1;
    Shop shop2;

    @Before
    public void setup() {
        goods = new Goods("test", "code");
        shop = new Shop();
        goods1 = new Goods("t1", "t2");
        goods2 = new Goods("tt1", "tt2");
        goods3 = new Goods("ttt1", "ttt2");
        shop1 = new Shop();
        shop2 = new Shop();
    }

    @Test
    public void testConstructor() {
        Map<String, Goods> test1 = new LinkedHashMap<>();
        Shop shop1 = new Shop();
        test1 = shop1.getShelves();
        Map<String, Goods> test2 = new LinkedHashMap<>();
        test2 = new LinkedHashMap<>();
        test2.put("Shelves1", null);
        test2.put("Shelves2", null);
        test2.put("Shelves3", null);
        test2.put("Shelves4", null);
        test2.put("Shelves5", null);
        test2.put("Shelves6", null);
        test2.put("Shelves7", null);
        test2.put("Shelves8", null);
        test2.put("Shelves9", null);
        test2.put("Shelves10", null);
        test2.put("Shelves11", null);
        test2.put("Shelves12", null);
        Assert.assertEquals(test1, test2);
    }

    @Test
    public void testAdd() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        Assert.assertEquals(1, 1);
        Assert.assertEquals(shelves.get("Shelves1").getGoodsCode(), "t2");
        Assert.assertEquals(shelves.get("Shelves1").getName(), "t1");
        Goods goods2 = new Goods("smotan", "kok");
        shop2.addGoods("Shelves5", goods2);
        Map<String, Goods> shelves1 = shop2.getShelves();
        Assert.assertEquals(shelves1.get("Shelves5").getName(), "smotan");
        Assert.assertEquals(shelves1.get("Shelves5").getGoodsCode(), "kok");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNoExistShelvesShouldThrowErr() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        Assert.assertEquals(1, 1);
        Assert.assertEquals(shelves.get("Shelves1").getGoodsCode(), "t2");
        Assert.assertEquals(shelves.get("Shelves1").getName(), "t1");
        shop1.addGoods("Shelves1", goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithSameShelvesShouldThrowErr() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        Assert.assertEquals(1, 1);
        Assert.assertEquals(shelves.get("Shelves1").getGoodsCode(), "t2");
        Assert.assertEquals(shelves.get("Shelves1").getName(), "t1");
        shop1.addGoods("Shelves111", goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithExistItemShouldThrowErr() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        Assert.assertEquals(1, 1);
        Assert.assertEquals(shelves.get("Shelves1").getGoodsCode(), "t2");
        Assert.assertEquals(shelves.get("Shelves1").getName(), "t1");
        shop1.addGoods("Shelves1", goods1);
        assertTrue(shop1.getShelves().containsValue(goods1));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodBoolean() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        shop1.addGoods("Shelves2", goods1);

    }

    @Test
    public void testRemoveGoods() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        shop1.addGoods("Shelves2", goods2);
        shop1.addGoods("Shelves5", goods3);
        Map<String, Goods> shelves = shop1.getShelves();
        Goods shelves5 = shelves.get("Shelves5");
        shop1.removeGoods("Shelves1", goods1);
        assertNull(shelves.get("Shelves1"));
        shop1.removeGoods("Shelves2", goods2);
        assertNull(shelves.get("Shelves2"));
        shop1.removeGoods("Shelves5", goods3);
        assertNull(shelves.get("Shelves5"));
        Assert.assertNotEquals(shelves5, shelves.get("Shelves5"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsWithNoExistingShelf() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        shop1.removeGoods("asd", goods1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsWithDifferentShelf() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        shop1.removeGoods("Shelves1", goods2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsWithNoExistingShelfAndGods() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);
        Map<String, Goods> shelves = shop1.getShelves();
        shop1.removeGoods("dreqw", goods2);

    }

    @Test
    public void testStringFormatRemove() throws OperationNotSupportedException {
        shop1.addGoods("Shelves1", goods1);

        Assert.assertEquals("Goods: t2 is removed successfully!", shop1.removeGoods("Shelves1", goods1));
    }


}