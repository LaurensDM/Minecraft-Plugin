package plugins.exp_nightvision_plug.enchant;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import plugins.exp_nightvision_plug.EXP_NightVision_PLUG;

import java.util.HashSet;
import java.util.Set;

public class NightVisionEnchant extends Enchantment implements Listener {
    public NightVisionEnchant() {
        super(NamespacedKey.minecraft("night_vision"));
    }

    @EventHandler
    public void onEquip(PlayerArmorChangeEvent event) {
        if (event.getSlotType() != PlayerArmorChangeEvent.SlotType.HEAD) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack helmet = event.getNewItem();
        ItemStack oldHelmet = event.getOldItem();

        if(helmet != null && helmet.containsEnchantment(this)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            PotionEffect effect = new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 0, true, false);
            player.addPotionEffect(effect);
        }

        if ((helmet == null || !helmet.containsEnchantment(this)) && (oldHelmet != null && oldHelmet.containsEnchantment(this))) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
    }

    /**
     * @deprecated
     */
    @Override
    public @NotNull String getName() {
        return "Night vision";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    /**
     * Gets the level that this Enchantment should start at
     *
     * @return Starting level of the Enchantment
     */
    @Override
    public int getStartLevel() {
        return 0;
    }


    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text("Night vision "+i);
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return EnchantmentRarity.COMMON;
    }

    @Override
    public float getDamageIncrease(int i, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    /**
     * Gets the equipment slots where this enchantment is considered "active".
     *
     * @return the equipment slots
     */
    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        Set<EquipmentSlot> slots = new HashSet<>();
        slots.add(EquipmentSlot.HEAD);
        return slots;
    }

    /**
     * @return
     */
    @Override
    public @NotNull String translationKey() {
        return "night_vision";
    }
}
