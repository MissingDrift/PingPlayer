package missingdrift.pingplayer;

import missingdrift.pingplayer.commands.PingCommand;
import missingdrift.pingplayer.commands.ReloadCommand;
import missingdrift.pingplayer.tablist.PingTabList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Pingplayer extends JavaPlugin {
    private static Pingplayer instance;

    public static Pingplayer getInstance() {
        return instance;
    }

    public void onEnable() {
        System.out.println("  ____ ___ _   _  ____   ____  _        _ __   _______ ____  \n" +
                " |  _ \\_ _| \\ | |/ ___| |  _ \\| |      / \\\\ \\ / / ____|  _ \\ \n" +
                " | |_) | ||  \\| | |  _  | |_) | |     / _ \\\\ V /|  _| | |_) |\n" +
                " |  __/| || |\\  | |_| | |  __/| |___ / ___ \\| | | |___|  _ < \n" +
                " |_|  |___|_| \\_|\\____| |_|   |_____/_/   \\_\\_| |_____|_| \\_\\\n" +
                "                                                             " +
                "\n" +
                "Version: 1.0.0\n" +
                "Author: MissingDrift");
        instance = this;
        saveDefaultConfig();
        getCommand("ping").setExecutor((CommandExecutor)new PingCommand(this));
        getCommand("pingreload").setExecutor((CommandExecutor)new ReloadCommand(this));
        registerTasks();
    }

    public void onDisable() {
        instance = null;
        getLogger().info("Cancelling tasks...");
        getServer().getScheduler().cancelTasks((Plugin)this);
    }

    private void registerTasks() {
        if (getConfig().getBoolean("permission-system.enabled"))
            getLogger().info("The permission-system is enabled. Please check that users have proper permissions.");
        if (!getConfig().getBoolean("sound-manager.enabled"))
            getLogger().info("The sound manager is disabled, no sounds will be played on commands. You can change this option in the config.");
        if (!getConfig().getBoolean("tablist.enabled")) {
            getLogger().info("The tablist is disabled, the ping will not be shown as a prefix. You can change this option in the config.");
        } else {
            Long delay = Long.valueOf(getConfig().getLong("tablist.update-delay"));
            (new PingTabList(this)).runTaskTimerAsynchronously((Plugin)this, delay.longValue() * 20L, delay.longValue() * 20L);
            getLogger().info("TabList is enabled, task added with a delay of " + delay + " second/s.");
        }
    }

    public void reload() {
        getLogger().info("Reloading the plugin...");
        getServer().getScheduler().cancelTasks((Plugin)this);
        reloadConfig();
        registerTasks();
        getLogger().info("Plugin reloaded.");
    }
}
