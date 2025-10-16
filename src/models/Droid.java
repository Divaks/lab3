package models;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }

    public abstract String getType();

    public abstract int attack();

    @Override
    public String toString() {
        return String.format("%s [%s] - HP: %d/%d, Урон: %d",
                name, getType(), health, maxHealth, damage);
    }
}