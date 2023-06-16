package plugins.exp_nightvision_plug;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import plugins.exp_nightvision_plug.enchant.NightVisionEnchant;
import plugins.exp_nightvision_plug.listener.DeathListener;
import plugins.exp_nightvision_plug.listener.EnchantListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public final class EXP_NightVision_PLUG extends JavaPlugin {
    private static EXP_NightVision_PLUG plugin;
    private final ArrayList<Enchantment> custom_enchants = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        NightVisionEnchant nightVisionEnchant = new NightVisionEnchant();

        custom_enchants.add(nightVisionEnchant);

        registerEnchantments(nightVisionEnchant);

        this.getServer().getPluginManager().registerEvents(nightVisionEnchant, this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new EnchantListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchantment : custom_enchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }

    }

    public static EXP_NightVision_PLUG getPlugin() {
        return plugin;
    }

    public void registerEnchantments(Enchantment enchantment){
        boolean registered = true;
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            System.out.println("Enchantment registered");
            super.getSLF4JLogger().info("Enchantment registered");
        }
    }



}
