package me.ponktacology.stone.listener;

import me.ponktacology.stone.Stone;
import me.ponktacology.stone.generator.Generator;
import me.ponktacology.stone.manager.StoneManager;
import me.ponktacology.stone.util.CC;
import me.ponktacology.stone.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onMine(BlockBreakEvent event) {
    if (!event.getBlock().getType().equals(Material.STONE)) {
      return;
    }

    if (event.getPlayer().getItemInHand() == null) {
      return;
    }

    Player player = event.getPlayer();
    Block stone = event.getBlock();
    Generator generator = StoneManager.getGenerators().get(stone.getLocation());

    if (generator == null) {
      return;
    }

    if (player.getItemInHand().getType().equals(Material.GOLD_PICKAXE)) {
      stone.setType(Material.AIR);
      ItemStack stoniarka = new ItemBuilder(Stone.getInstance().getStoneConfig().getMaterial()).setName(
          CC.translate("&e&lStoniarka")).addEnchant(Enchantment.DURABILITY, 10)
          .addLoreLine(CC.translate("&cUzycia: &f" + generator.getDurability())).addItemFlags(
              ItemFlag.HIDE_ENCHANTS).toItemStack();

      stone.getLocation().getWorld().dropItemNaturally(stone.getLocation(), stoniarka);
      player.sendMessage(CC.translate("&eZniszczyles stoniarke!"));
      StoneManager.getGenerators().remove(generator.getBlockLocation());
    } else {
      generator.setDurability(generator.getDurability() - 1);
      generator.regen();
    }
  }

}
