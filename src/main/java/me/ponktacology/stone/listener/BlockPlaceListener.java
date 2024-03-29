package me.ponktacology.stone.listener;


import me.ponktacology.stone.generator.Generator;
import me.ponktacology.stone.util.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

  @EventHandler
  public void onPlace(BlockPlaceEvent event) {
    Player player = event.getPlayer();

    if(event.getItemInHand().getItemMeta().getDisplayName() == null) {
      return;
    }

    if (event.getItemInHand().getItemMeta().getDisplayName()
        .equals(Color.translate("&e&lStoniarka"))) {
      int durability = Integer.valueOf(event.getItemInHand().getItemMeta().getLore().get(0)
          .replace(Color.translate("&cUzycia: &f"), ""));
      new Generator(event.getBlockPlaced().getLocation(), durability);
      event.getBlockPlaced().setType(Material.STONE);
      player.sendMessage(
          Color.translate("&eStworzyles stoniarke! Zeby ja zniszczyc musisz uzyc zlotego kilofa."));
    }
  }
}
