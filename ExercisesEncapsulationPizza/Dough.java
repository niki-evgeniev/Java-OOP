package ExercisesEncapsulationPizza;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        switch (flourType) {
            case "Wholegrain":
            case "White":
                this.flourType = flourType;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setWeight(double weight) {
        if (weight > 200 || weight < 1) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        double typeModifiers = 0;
        if (flourType.equals("White")) {
            typeModifiers = 1.5;
        } else if (flourType.equals("Wholegrain")) {
            typeModifiers = 1.0;
        }
        double bakingModifiers = 0;
        if (bakingTechnique.equals("Crispy")) {
            bakingModifiers = 0.9;
        } else if (bakingTechnique.equals("Chewy")) {
            bakingModifiers = 1.1;
        } else if (bakingTechnique.equals("Homemade")) {
            bakingModifiers = 1.0;
        }
        return (2 * weight) * typeModifiers * bakingModifiers;
    }
}
