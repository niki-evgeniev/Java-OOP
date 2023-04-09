package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class PetStoreTests {

    private Animal animal;
    private Animal animal1;
    private Animal animal2;
    private PetStore petStore;

    @Before
    public void setup() {
        animal = new Animal("testSp", 20, 5.55);
        animal1 = new Animal("testSp1", 30, 15.55);
        animal2 = new Animal("testSp2", 40, 55.55);
        petStore = new PetStore();
    }

    @Test
    public void testAddAnimal() {
        petStore.addAnimal(animal);
        Assert.assertEquals(animal.getSpecie(), "testSp");
        Assert.assertEquals(animal.getMaxKilograms(), 20);
        Assert.assertEquals(animal.getPrice(), 5.55, 0.00);
        Assert.assertEquals(petStore.getCount(), 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalWithNull() {
        petStore.addAnimal(null);
        Assert.assertNull(petStore.getAnimals());
    }

    @Test
    public void testFindAllAnimalsWithMaxKilograms() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        List<Animal> an = petStore.findAllAnimalsWithMaxKilograms(25);
        Assert.assertEquals(an.size(), 2);
        Assert.assertEquals(an.get(0).getMaxKilograms(), 30);
        Assert.assertEquals(an.get(1).getMaxKilograms(), 40);

    }

    @Test
    public void testGetAnimalsCollection() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        List<Animal> an = petStore.getAnimals();
        Assert.assertEquals(an.size(), 3);
    }

    //    @Test (expected = UnsupportedOperationException.class)
//    public void testGetAnimalsCollectionUnmodifi(){
//        petStore.addAnimal(animal);
//        petStore.addAnimal(animal1);
//        petStore.addAnimal(animal2);
//        List<Animal> an = petStore.getAnimals();
//        Assert.assertEquals(an.size(), 3);
//        an.remove(1);
//    }
    @Test
    public void testGetTheMostExpensiveAnimal() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        Animal animalExpressive = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(animalExpressive, animal2);
    }
    @Test
    public void testFindAllAnimalBySpecie(){
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
       // Animal animal3 = petStore.findAllAnimalBySpecie("testSp1");
        List<Animal> testList = petStore.findAllAnimalBySpecie("testSp1");
        Assert.assertEquals(testList.get(0),animal1);

    }

}

