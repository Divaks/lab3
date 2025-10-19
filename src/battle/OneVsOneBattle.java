package battle;

import model.Droid;

public class OneVsOneBattle extends AbstractBattle {

    public OneVsOneBattle() {
        super();
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

            if (!performAttack(droid1, droid2, droid1.getName(), droid2.getName())) {
                logger.log("\n🏆 Переможець: " + droid1.getName() + "!");
                break;
            }

            logger.log("");

            // Другий дроїд атакує
            if (!performAttack(droid2, droid1, droid2.getName(), droid1.getName())) {
                logger.log("\n🏆 Переможець: " + droid2.getName() + "!");
                break;
            }

            logger.log("");
            round++;
        }

        return logger;
    }
}