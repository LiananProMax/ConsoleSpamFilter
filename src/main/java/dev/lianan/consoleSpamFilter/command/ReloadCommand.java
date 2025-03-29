package dev.lianan.consoleSpamFilter.command;

import dev.lianan.consoleSpamFilter.ConsoleSpamFilter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabExecutor {

    private final ConsoleSpamFilter plugin;

    public ReloadCommand(ConsoleSpamFilter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("consolefilter.reload")) {
            sender.spigot().sendMessage(plugin.getNoPermissionMessage());
            return true;
        }

        plugin.loadConfigs();
        sender.spigot().sendMessage(plugin.getConfigReloadedMessage());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return new ArrayList<>();
    }
}
