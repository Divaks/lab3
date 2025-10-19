package model;

import java.util.Random;

public class MedicDroid extends Droid {
    private static final Random random = new Random();

    public MedicDroid(String name) {
        super(name, 100, 10);
    }

    public String getType() {
        return "Медик";
    }

    public int attack() {
        // Медик може лікувати себе під час атаки
        if (random.nextInt(100) < 25) {
            heal(20);
        }
        return damage;
    }
}