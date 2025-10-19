import model.*;
import battle.*;
import utils.FileManager;
import java.util.*;

public class Main {
    private static List<Droid> droids = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static BattleLogger lastBattleLogger = null;

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getIntInput("Виберіть опцію: ");

            switch (choice) {
                case 1 -> createDroid();
                case 2 -> showDroids();
                case 3 -> startOneVsOneBattle();
                case 4 -> startTeamBattle();
                case 5 -> saveBattleToFile();
                case 6 -> replayBattleFromFile();
                case 7 -> {
                    System.out.println("Програму завершено.");
                    return;
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити бій команда на команду");
        System.out.println("5. Зберегти бій у файл");
        System.out.println("6. Відтворити бій з файлу");
        System.out.println("7. Вийти");
    }

    private static void createDroid() {
        System.out.println("\nВиберіть тип дроїда:");
        System.out.println("1. Штурмовик (HP: 120, Шкода: 25, Особливість: шанс додаткової шкоди)");
        System.out.println("2. Танк (HP: 200, Шкода: 15, Особливість: зменшена шкода)");
        System.out.println("3. Снайпер (HP: 80, Шкода: 35, Особливість: критична шкода)");
        System.out.println("4. Медик (HP: 100, Шкода: 10, Особливість: самолікування)");

        int type = getIntInput("Тип: ");
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        Droid droid = switch (type) {
            case 1 -> new AssaultDroid(name);
            case 2 -> new TankDroid(name);
            case 3 -> new SniperDroid(name);
            case 4 -> new MedicDroid(name);
            default -> null;
        };

        if (droid != null) {
            droids.add(droid);
            System.out.println("Дроїда створено: " + droid);
        } else {
            System.out.println("Невірний тип дроїда");
        }
    }

    private static void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("\nСписок дроїдів порожній");
            return;
        }

        System.out.println("Список дроїдів:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i));
        }
    }

    private static void startOneVsOneBattle() {
        if (droids.size() < 2) {
            System.out.println("\nНедостатньо дроїдів для бою (потрібно мінімум 2)");
            return;
        }

        showDroids();
        int idx1 = getIntInput("\nВиберіть першого дроїда: ") - 1;
        int idx2 = getIntInput("Виберіть другого дроїда: ") - 1;

        if (idx1 < 0 || idx1 >= droids.size() || idx2 < 0 || idx2 >= droids.size() || idx1 == idx2) {
            System.out.println("Невірний вибір дроїдів");
            return;
        }

        // Клонуємо дроїдів для бою (щоб зберегти оригінали)
        Droid d1 = cloneDroid(droids.get(idx1));
        Droid d2 = cloneDroid(droids.get(idx2));

        OneVsOneBattle battle = new OneVsOneBattle();
        lastBattleLogger = battle.startBattle(d1, d2);
    }

    private static void startTeamBattle() {
        if (droids.size() < 2) {
            System.out.println("\nНедостатньо дроїдів для командного бою");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("\nФормування команди 1:");
        formTeam(team1);

        System.out.println("\nФормування команди 2:");
        formTeam(team2);

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Обидві команди повинні мати хоча б одного дроїда");
            return;
        }

        TeamBattle battle = new TeamBattle();
        lastBattleLogger = battle.startBattle(team1, team2);
    }

    private static void formTeam(List<Droid> team) {
        while (true) {
            showDroids();
            int idx = getIntInput("\nВиберіть дроїда (0 для завершення): ") - 1;

            if (idx == -1) break;

            if (idx >= 0 && idx < droids.size()) {
                team.add(cloneDroid(droids.get(idx)));
                System.out.println("✓ Дроїда додано до команди");
            } else {
                System.out.println("✗ Невірний вибір");
            }
        }
    }

    private static void saveBattleToFile() {
        if (lastBattleLogger == null) {
            System.out.println("\nНемає бою для збереження. Спочатку проведіть бій.");
            return;
        }

        System.out.print("Введіть ім'я файлу (без розширення): ");
        String filename = scanner.nextLine() + ".txt";
        FileManager.saveBattle(lastBattleLogger.getLogs(), filename);
    }

    private static void replayBattleFromFile() {
        System.out.print("Введіть ім'я файлу: ");
        String filename = scanner.nextLine();
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }
        FileManager.replayBattle(filename);
    }

    private static Droid cloneDroid(Droid original) {
        return switch (original) {
            case AssaultDroid d -> new AssaultDroid(d.getName());
            case TankDroid d -> new TankDroid(d.getName());
            case SniperDroid d -> new SniperDroid(d.getName());
            case MedicDroid d -> new MedicDroid(d.getName());
            default -> null;
        };
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть число");
            }
        }
    }
}