package battle;

import model.Droid;
import java.util.Random;

public abstract class AbstractBattle {
    protected BattleLogger logger;
    protected static final Random random = new Random();

    public AbstractBattle() {
        this.logger = new BattleLogger();
    }

    protected boolean performAttack(Droid attacker, Droid target, String attackerName, String targetName) {
        if (attacker == null || target == null) {
            return true;
        }

        int damage = attacker.attack();
        target.takeDamage(damage);

        logger.log(attackerName + " атакує " + targetName +
                " і наносить " + damage + " урону");
        logger.log(target.getName() + " HP: " + target.getHealth() + "/" + target.getMaxHealth());

        if (!target.isAlive()) {
            logger.log(target.getName() + " знищений!");
            return false;
        }

        return true;
    }
}
