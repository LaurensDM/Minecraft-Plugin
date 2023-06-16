package plugins.exp_nightvision_plug.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setKeepLevel(true);
        event.setShouldDropExperience(false);
        Player player = event.getEntity();
        int level = player.getLevel();
        int newLevel = (int) Math.floor(level *0.7);
        player.setLevel(newLevel);
        player.setTotalExperience(0);
    }
}
