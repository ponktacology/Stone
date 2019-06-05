package me.ponktacology.stone.config;

import me.ponktacology.stone.Stone;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public final class StoneConfig {

  private final String mongoLink;
  private final int durability;
  private final int regenTime;
  private final Material material, slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9;

  public StoneConfig() {
    Stone stone = Stone.getInstance();
    this.mongoLink = stone.getConfig().getString("mongo_link");
    this.durability = stone.getConfig().getInt("durability");
    this.regenTime = stone.getConfig().getInt("regen_time");
    this.material = Material.valueOf(stone.getConfig().getString("material"));
    this.slot1 = Material.valueOf(stone.getConfig().getString("slot1"));
    this.slot2 = Material.valueOf(stone.getConfig().getString("slot2"));
    this.slot3 = Material.valueOf(stone.getConfig().getString("slot3"));
    this.slot4 = Material.valueOf(stone.getConfig().getString("slot4"));
    this.slot5 = Material.valueOf(stone.getConfig().getString("slot5"));
    this.slot6 = Material.valueOf(stone.getConfig().getString("slot6"));
    this.slot7 = Material.valueOf(stone.getConfig().getString("slot7"));
    this.slot8 = Material.valueOf(stone.getConfig().getString("slot8"));
    this.slot9 = Material.valueOf(stone.getConfig().getString("slot9"));
  }

}
