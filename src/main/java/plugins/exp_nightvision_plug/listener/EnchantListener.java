package plugins.exp_nightvision_plug.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import plugins.exp_nightvision_plug.enchant.NightVisionEnchant;

public class EnchantListener implements Listener {

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        if (item.getType() == Material.TURTLE_HELMET){
            event.getEnchantsToAdd().put(new NightVisionEnchant(), 1);
        }
    }
}
