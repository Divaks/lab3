package utils;

import java.io.*;
import java.util.List;

public class FileManager {

    public static void saveBattle(List<String> logs, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String log : logs) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println("Бій збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні файлу: " + e.getMessage());
        }
    }

    public static void replayBattle(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("\n=== ВІДТВОРЕННЯ БОЮ ===\n");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(500); // Пауза для ефекту відтворення
            }
            System.out.println("\n=== КІНЕЦЬ ВІДТВОРЕННЯ ===\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Відтворення перервано");
        }
    }
}