package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift gift;

    @Before
    public void setup() {
        giftFactory = new GiftFactory();
        gift = new Gift("test", 12.5);
    }

    @Test
    public void TestCreateGift() {
        giftFactory.createGift(gift);
    }
    @Test (expected = IllegalArgumentException.class)
    public void TestCreateGiftWithSameType() {
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);
    }

    @Test
    public void testRemoveGift(){
        giftFactory.createGift(gift);
        giftFactory.removeGift("test");
        Assert.assertTrue(true);
    }
    @Test (expected = NullPointerException.class)
    public void testRemoveGiftWithMissingName(){
        giftFactory.createGift(gift);
        giftFactory.removeGift("   ");
        Assert.assertFalse( false);
    }
    @Test (expected = NullPointerException.class)
    public void testRemoveGiftWithNull(){
        giftFactory.createGift(gift);
        giftFactory.removeGift(null);
        Assert.assertFalse( false);
    }
    @Test
    public void testGetPresentWithLeastMagic(){
        giftFactory.createGift(gift);
        Gift gift1 = new Gift("test2", 3);
        giftFactory.createGift(gift1);
        Assert.assertEquals(gift1, giftFactory.getPresentWithLeastMagic());
    }
    @Test
    public void testGetPresentWithLeastMagicWithNull(){
        Assert.assertNull(giftFactory.getPresentWithLeastMagic());
    }
    @Test
    public void testGetPresent(){
        giftFactory.createGift(gift);
        Gift gift1 = new Gift("test2", 3);
        giftFactory.createGift(gift1);
        Assert.assertEquals(gift,giftFactory.getPresent("test"));
    }
    @Test
    public void testGetPresentWithNull(){
    Assert.assertNull(giftFactory.getPresent("test"));
    }
    @Test
    public void testGetPresentsToReturnCollections(){
        giftFactory.createGift(gift);
        Gift gift1 = new Gift("test2", 3);
        Gift gift2 = new Gift("test3", 5);
        giftFactory.createGift(gift1);
        giftFactory.createGift(gift2);
        Collection<Gift> presents = giftFactory.getPresents();
        Assert.assertEquals(presents.size(),3);
    }
    @Test
    public void testGetCount(){
        giftFactory.createGift(gift);
        Assert.assertEquals(giftFactory.getCount(),1);
    }


}
