package battle;

import model.Droid;
import java.util.List;

public class TeamBattle extends AbstractBattle {

    public TeamBattle() {
        super();
    }

    public BattleLogger startBattle(List<Droid> team1, List<Droid> team2) {
        logger.clear();
        logger.log("=== БІЙ КОМАНДА НА КОМАНДУ ===");
        logger.log("Команда 1:");
        for (Droid d : team1) {
            logger.log("  " + d.toString());
        }
        logger.log("\nКоманда 2:");
        for (Droid d : team2) {
            logger.log("  " + d.toString());
        }
        logger.log("===============================\n");

        int round = 1;
        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            logger.log("--- Раунд " + round + " ---");

            Droid attacker1 = getRandomAliveDroid(team1);
            Droid target1 = getRandomAliveDroid(team2);

            String attacker1Name = (attacker1 != null) ? attacker1.getName() + " (Команда 1)" : "Хтось (Команда 1)";
            String target1Name = (target1 != null) ? target1.getName() + " (Команда 2)" : "Хтось (Команда 2)";

            if (!performAttack(attacker1, target1, attacker1Name, target1Name)) {
                if (!hasAliveDroids(team2)) {
                    logger.log("\n🏆 Перемогла Команда 1!");
                    break;
                }
            }

            if (!hasAliveDroids(team2)) {
                logger.log("\n🏆 Перемогла Команда 1!");
                break;
            }

            logger.log("");

            Droid attacker2 = getRandomAliveDroid(team2);
            Droid target2 = getRandomAliveDroid(team1);

            String attacker2Name = (attacker2 != null) ? attacker2.getName() + " (Команда 2)" : "Хтось (Команда 2)";
            String target2Name = (target2 != null) ? target2.getName() + " (Команда 1)" : "Хтось (Команда 1)";

            if (!performAttack(attacker2, target2, attacker2Name, target2Name)) {
                if (!hasAliveDroids(team1)) {
                    logger.log("\n🏆 Перемогла Команда 2!");
                    break;
                }
            }

            if (!hasAliveDroids(team1)) {
                logger.log("\n🏆 Перемогла Команда 2!");
                break;
            }

            logger.log("");
            round++;
        }

        return logger;
    }

    private boolean hasAliveDroids(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    private Droid getRandomAliveDroid(List<Droid> team) {
        List<Droid> aliveDroids = team.stream()
                .filter(Droid::isAlive)
                .toList();

        if (aliveDroids.isEmpty()) {
            return null;
        }

        return aliveDroids.get(random.nextInt(aliveDroids.size()));
    }
}