package org.example;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Instock implements ProductStock {
   private List<Product> productList;

    public Instock() {
        this.productList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean contains(Product product) {
        String productLabel = product.getLabel();
        for (Product product1 : productList) {
            if (product1.getLabel().equals(productLabel)){
                return true;
            }
        }
//        return true;
//        boolean som = productList.stream().anyMatch(p -> p.getLabel().equals(product.getLabel()));
//       return som;
        return false;
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {

    }

    @Override
    public Product find(int index) {
        return null;
    }

    @Override
    public Product findByLabel(String label) {
        return null;
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        return null;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return null;
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return null;
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        return null;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return null;
    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }
}
