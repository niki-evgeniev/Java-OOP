package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car car;
    private Car car1;
    private Car car2;
    private Car car3;

    @Before
    public void setUp() {
        car = new Car("test", 250, 2500.5);
        garage = new Garage();
        car1 = new Car("test1", 220, 3500.5);
        car2 = new Car("test2", 120, 1200.5);
        car3 = new Car("test2", 160, 13500.5);
        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
    }

    @Test
    public void testConstructor() {
        garage.addCar(car);
    }

    @Test
    public void testGetCount() {
        int count = garage.getCount();
        Assert.assertEquals(4, count);
    }

    @Test
    public void testGetCars() {
        List<Car> cars = garage.getCars();
        Assert.assertEquals(4, cars.size());
    }

    @Test
    public void testFindCarWithMaxSpeed() {
        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(219);
        Assert.assertEquals(2, cars.size());
        Assert.assertEquals(250, cars.get(0).getMaxSpeed());
        Assert.assertEquals(220, cars.get(1).getMaxSpeed());
    }

    @Test
    public void addCar() {
        Car carToAdd = new Car("Honda", 220, 1500);
        garage.addCar(carToAdd);
        List<Car> cars = garage.getCars();
        Car carToSee = cars.get(4);
        Assert.assertEquals(carToAdd, carToSee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCarWithNull() {
        garage.addCar(null);
    }

    @Test
    public void testMostExpensive() {
        Assert.assertEquals(car3, garage.getTheMostExpensiveCar());
    }

    @Test
    public void testAllCarsByBrad() {
        List<Car> test1 = garage.findAllCarsByBrand("test1");
        List<Car> cars = garage.getCars();
        Assert.assertEquals(cars.get(1), test1.get(0));

    }

}