package com.jelizy.stone;

import com.jelizy.stone.config.StoneConfig;
import com.jelizy.stone.data.StoneData;
import com.jelizy.stone.listener.BlockBreakListener;
import com.jelizy.stone.listener.BlockPlaceListener;
import com.jelizy.stone.util.CC;
import com.jelizy.stone.util.ItemBuilder;
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

    ItemStack stoniarka = new ItemBuilder(this.stoneConfig.getMaterial()).setName(
        CC.translate("&e&lStoniarka")).addEnchant(Enchantment.DURABILITY, 10)
        .addLoreLine(CC.translate("&cUzycia: &f" + this.stoneConfig.getDurability())).addItemFlags(
            ItemFlag.HIDE_ENCHANTS).toItemStack();

    ShapedRecipe recipe = new ShapedRecipe(stoniarka);
    recipe.shape("123", "456", "789");
    recipe.setIngredient('1', this.stoneConfig.getSlot1());
    recipe.setIngredient('2', this.stoneConfig.getSlot2());
    recipe.setIngredient('3', this.stoneConfig.getSlot3());
    recipe.setIngredient('4', this.stoneConfig.getSlot4());
    recipe.setIngredient('5', this.stoneConfig.getSlot5());
    recipe.setIngredient('6', this.stoneConfig.getSlot6());
    recipe.setIngredient('7', this.stoneConfig.getSlot7());
    recipe.setIngredient('8', this.stoneConfig.getSlot8());
    recipe.setIngredient('9', this.stoneConfig.getSlot9());
    Bukkit.addRecipe(recipe);

    Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
  }

  @Override
  public void onDisable() {
    StoneData.saveAll();
  }
}
