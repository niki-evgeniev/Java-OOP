package ExercisesEncapsulationPizza;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        switch (toppingType){
            case"Meat":
            case"Veggies":
            case"Cheese":
            case"Sauce":
                this.toppingType = toppingType;
                break;
            default:
                String errorMessages = "Cannot place " + toppingType + " on top of your pizza.";
                throw  new IllegalArgumentException(errorMessages);
        }

    }

    private void setWeight(double weight) {
        if (weight > 50 || weight < 1){
            String errorMessages = String.format("%s weight should be in the range [1..50].", toppingType);
            throw new IllegalArgumentException(errorMessages);
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        switch (toppingType) {
            case "Meat":
                return (2 * weight) * 1.2;
            case "Veggies":
                return (2 * weight) * 0.8;
            case "Cheese":
                return (2 * weight) * 1.1;
            case "Sauce":
                return (2 * weight) * 0.9;
            default:
                String errorMessages = "Cannot place " + toppingType + " on top of your pizza.";
                throw  new IllegalArgumentException(errorMessages);
        }
    }
}
