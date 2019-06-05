package me.ponktacology.stone;

import me.ponktacology.stone.config.StoneConfig;
import me.ponktacology.stone.data.StoneData;
import me.ponktacology.stone.listener.BlockBreakListener;
import me.ponktacology.stone.listener.BlockPlaceListener;
import me.ponktacology.stone.util.Color;
import me.ponktacology.stone.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public final class Stone extends JavaPlugin {

  @Getter
  private static Stone instance;
  private StoneConfig stoneConfig;
  private StoneData stoneData;

  @Override
  public void onEnable() {
    instance = this;
    this.saveDefaultConfig();
    this.stoneConfig = new StoneConfig();
    this.stoneData = new StoneData();

    Bukkit.getScheduler().runTaskAsynchronously(this, StoneData::loadAll);

    ItemStack generator = new ItemBuilder(this.stoneConfig.getMaterial()).setName(
        Color.translate("&e&lStoniarka")).addEnchant(Enchantment.DURABILITY, 10)
        .addLoreLine(Color.translate("&cUzycia: &f" + this.stoneConfig.getDurability()))
        .addItemFlags(
            ItemFlag.HIDE_ENCHANTS).toItemStack();

    ShapedRecipe recipe = new ShapedRecipe(generator);
    recipe.shape("123", "456", "789");
    for (int x = 1; x <= 9; x++) {
      recipe.setIngredient(Character.toChars('0' + x)[0], this.stoneConfig.getCrafting().get(x - 1));
    }
    Bukkit.addRecipe(recipe);

    Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
  }

  @Override
  public void onDisable() {
    StoneData.saveAll();
  }
}
