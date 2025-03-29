package dev.lianan.consoleSpamFilter;

import dev.lianan.consoleSpamFilter.command.ReloadCommand;
import dev.lianan.consoleSpamFilter.share.ConsoleFilterShare;
import dev.lianan.consoleSpamFilter.share.LogFilter;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class ConsoleSpamFilter extends JavaPlugin {

    @Getter
    private TextComponent noPermissionMessage;

    @Getter
    private TextComponent configReloadedMessage;

    private long keepLogs;

    @Getter
    private List<String> filter;

    @Override
    public void onEnable() {
        loadConfigs();

        getCommand("reloadconsolefilter").setExecutor(new ReloadCommand(this));

        ((Logger) LogManager.getRootLogger()).addFilter(new LogFilter(filter));

        getLogger().info("Loaded successfully! Console will now be filtered.");

        if (keepLogs > 0) {
            ConsoleFilterShare.cleanupLogs(this, keepLogs);
        }
    }

    public void loadConfigs() {
        saveDefaultConfig();
        reloadConfig();

        noPermissionMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("no-permission-message", "&cYou lack the permission &7consolefilter.reload")));

        configReloadedMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("config-reloaded-message", "&cConsoleFilter &aConfig reloaded!")));

        keepLogs = getConfig().getLong("keep-logs", 7) * 24 * 60 * 60 * 1000;
        filter = getConfig().getStringList("filter");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }
}
