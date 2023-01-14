package missingdrift.pingplayer.utils;

import missingdrift.pingplayer.Pingplayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {
    public static void playSound(Player p, String soundType) {
        float volume = Float.parseFloat(Pingplayer.getInstance().getConfig().getString("sound-manager.volume"));
        float pitch = Float.parseFloat(Pingplayer.getInstance().getConfig().getString("sound-manager.pitch"));
        try {
            p.playSound(p.getLocation(), Sound.valueOf(soundType), volume, pitch);
        } catch (IllegalArgumentException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PlayerPing] Impossibile riprodurre un suono, controllare il file config.");
        }
    }
}
