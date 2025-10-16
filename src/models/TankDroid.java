package models;

public class TankDroid extends Droid {

    public TankDroid(String name) {
        super(name, 200, 15);
    }

    public String getType() {
        return "Танк";
    }

    public int attack() {
        return damage;
    }

    public void takeDamage(int damage) {
        // Танк отримує на 20% менше урону
        super.takeDamage((int)(damage * 0.8));
    }
}

