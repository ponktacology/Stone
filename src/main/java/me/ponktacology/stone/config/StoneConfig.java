package me.ponktacology.stone.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import me.ponktacology.stone.Stone;
import org.bukkit.Material;

@Getter
public final class StoneConfig {

  private final String mongoLink;
  private final int durability;
  private final int regenTime;
  private final Material material;
  private final List<Material> crafting;

  public StoneConfig() {
    Stone stone = Stone.getInstance();
    this.mongoLink = stone.getConfig().getString("mongo_link");
    this.durability = stone.getConfig().getInt("durability");
    this.regenTime = stone.getConfig().getInt("regen_time");
    this.material = Material.valueOf(stone.getConfig().getString("material"));
    this.crafting = new ArrayList<>();
    stone.getConfig().getList("crafting").forEach(mat ->
        this.crafting.add(Material.valueOf((String) mat))
    );
  }

}
