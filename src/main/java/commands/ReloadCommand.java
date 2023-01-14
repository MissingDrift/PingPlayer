package missingdrift.pingplayer.commands;

import missingdrift.pingplayer.Pingplayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private Pingplayer plugin;

    public ReloadCommand(Pingplayer plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
        if (!sender.hasPermission("PingPlayer.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("permission-system.no-perm-message")));
            return true;
        }
        this.plugin.reload();
        if (sender instanceof org.bukkit.entity.Player)
            sender.sendMessage(ChatColor.GREEN + "Plugin reloaded.");
        return true;
    }
}