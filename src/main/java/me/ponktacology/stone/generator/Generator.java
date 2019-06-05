package me.ponktacology.stone.generator;

import me.ponktacology.stone.Stone;
import me.ponktacology.stone.manager.StoneManager;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

@Data
public class Generator {

  private final Location blockLocation;
  private int durability;

  public Generator(Location blockLocation, int durability) {
    this.blockLocation = blockLocation;
    this.durability = durability;
    StoneManager.getGenerators().put(blockLocation, this);
  }

  public void regen() {
    Bukkit.getScheduler().runTaskLater(Stone.getInstance(), () -> {
      Block b = this.blockLocation.getBlock();
      b.setType(Material.STONE);
    }, 20 * Stone.getInstance().getStoneConfig().getRegenTime());
  }
}
