package ExercisesWorkingwithAbstraction;

public class LightChange {

    private Light light;


    public LightChange(Light light) {
        this.light = light;
    }

    public void changeLight(){
        switch (light){
            case RED:
                light = Light.GREEN;
                break;
            case GREEN:
                light = Light.YELLOW;
                break;
            case YELLOW:
                light = Light.RED;
                break;
        }
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }
}
