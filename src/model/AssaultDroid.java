package model;

import java.util.Random;

public class AssaultDroid extends Droid {
    private static final Random random = new Random();

    public AssaultDroid(String name) {
        super(name, 120, 25);
    }

    public String getType() {
        return "Штурмовик";
    }

    public int attack() {
        if (random.nextInt(100) < 20) {
            return damage + 15;
        }
        return damage;
    }
}