package me.ponktacology.stone.util;

import org.bukkit.ChatColor;

public class Color {

  public static String translate(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
}
