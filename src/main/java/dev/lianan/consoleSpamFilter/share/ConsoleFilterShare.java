package dev.lianan.consoleSpamFilter.share;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class ConsoleFilterShare {

    public static void cleanupLogs(JavaPlugin plugin, long keepDurationMillis) {
        File logsFolder = new File(plugin.getDataFolder().getParentFile().getParentFile(), "logs");
        File[] logFiles = logsFolder.listFiles((dir, name) -> name.endsWith(".log.gz"));
        if (logFiles == null) return;

        long cutoff = System.currentTimeMillis() - keepDurationMillis;
        for (File logFile : logFiles) {
            if (logFile.lastModified() < cutoff && logFile.delete()) {
                plugin.getLogger().info("Deleted old log: " + logFile.getName());
            }
        }
    }
}
