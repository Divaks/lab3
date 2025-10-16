package models;

import java.util.Random;

public class SniperDroid extends Droid {
    private static final Random random = new Random();

    public SniperDroid(String name) {
        super(name, 80, 35);
    }

    public String getType() {
        return "Снайпер";
    }

    public int attack() {
        // Снайпер має високий шанс критичного удару
        if (random.nextInt(100) < 30) {
            return damage * 2;
        }
        return damage;
    }
}