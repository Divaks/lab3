package battle;

import java.util.ArrayList;
import java.util.List;

public class BattleLogger {
    private List<String> logs;

    public BattleLogger() {
        this.logs = new ArrayList<>();
    }

    public void log(String message) {
        logs.add(message);
        System.out.println(message);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void clear() {
        logs.clear();
    }
}