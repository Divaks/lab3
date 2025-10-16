package battle;

import models.Droid;
import java.util.Random;

public class OneVsOneBattle {
    private static final Random random = new Random();
    private BattleLogger logger;

    public OneVsOneBattle() {
        this.logger = new BattleLogger();
    }

    public BattleLogger startBattle(Droid droid1, Droid droid2) {
        logger.clear();
        logger.log("=== БІЙ 1 на 1 ===");
        logger.log(droid1.toString());
        logger.log(droid2.toString());
        logger.log("===================\n");

        int round = 1;
        while (droid1.isAlive() && droid2.isAlive()) {
            logger.log("--- Раунд " + round + " ---");

            // Перший дроїд атакує
            int damage1 = droid1.attack();
            droid2.takeDamage(damage1);
            logger.log(droid1.getName() + " атакує " + droid2.getName() +
                    " і наносить " + damage1 + " шкоди");
            logger.log(droid2.getName() + " HP: " + droid2.getHealth() + "/" + droid2.getMaxHealth());

            if (!droid2.isAlive()) {
                logger.log("\n" + droid2.getName() + " знищений!");
                logger.log("🏆 Переможець: " + droid1.getName() + "!");
                break;
            }

            logger.log("");

            // Другий дроїд атакує
            int damage2 = droid2.attack();
            droid1.takeDamage(damage2);
            logger.log(droid2.getName() + " атакує " + droid1.getName() +
                    " і наносить " + damage2 + " шкоди");
            logger.log(droid1.getName() + " HP: " + droid1.getHealth() + "/" + droid1.getMaxHealth());

            if (!droid1.isAlive()) {
                logger.log("\n" + droid1.getName() + " знищений!");
                logger.log("🏆 Переможець: " + droid2.getName() + "!");
                break;
            }

            logger.log("");
            round++;
        }

        return logger;
    }
}