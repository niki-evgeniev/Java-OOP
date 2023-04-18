package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstockTest {

    private Product product;

    private ProductStock instock;


    @Before
    public void setup() {
        instock = new Instock();
        this.product = new Product("test", 12.54, 5);
    }

    @Test
    public void testAdd() {
      this.instock.add(product);
        Assert.assertTrue(instock.contains(product));
    }

    @Test
    public void testFindShuldReturnExceptionForIndexOutOfBound(){
        instock.find(1);
        Assert.assertEquals(product, 0);

    }


}