package battle;

import model.Droid;
import java.util.List;
import java.util.Random;

public class TeamBattle {
    private static final Random random = new Random();
    private BattleLogger logger;

    public TeamBattle() {
        this.logger = new BattleLogger();
    }

    public BattleLogger startBattle(List<Droid> team1, List<Droid> team2) {
        logger.clear();
        logger.log("БІЙ КОМАНДА НА КОМАНДУ:");
        logger.log("Команда 1:");
        for (Droid d : team1) {
            logger.log("  " + d.toString());
        }
        logger.log("\nКоманда 2:");
        for (Droid d : team2) {
            logger.log("  " + d.toString());
        }

        int round = 1;
        while (hasAliveDroids(team1) && hasAliveDroids(team2)) {
            logger.log("--- Раунд " + round + " ---");

            // Команда 1 атакує
            Droid attacker1 = getRandomAliveDroid(team1);
            Droid target1 = getRandomAliveDroid(team2);

            if (attacker1 != null && target1 != null) {
                int damage = attacker1.attack();
                target1.takeDamage(damage);
                logger.log(attacker1.getName() + " (Команда 1) атакує " +
                        target1.getName() + " (Команда 2) і наносить " + damage + " шкоди");
                logger.log(target1.getName() + " HP: " + target1.getHealth() + "/" + target1.getMaxHealth());

                if (!target1.isAlive()) {
                    logger.log(target1.getName() + " знищений!");
                }
            }

            if (!hasAliveDroids(team2)) {
                logger.log("\n🏆 Перемогла Команда 1!");
                break;
            }

            logger.log("");

            // Команда 2 атакує
            Droid attacker2 = getRandomAliveDroid(team2);
            Droid target2 = getRandomAliveDroid(team1);

            if (attacker2 != null && target2 != null) {
                int damage = attacker2.attack();
                target2.takeDamage(damage);
                logger.log(attacker2.getName() + " (Команда 2) атакує " +
                        target2.getName() + " (Команда 1) і наносить " + damage + " шкоди");
                logger.log(target2.getName() + " HP: " + target2.getHealth() + "/" + target2.getMaxHealth());

                if (!target2.isAlive()) {
                    logger.log(target2.getName() + " знищений!");
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