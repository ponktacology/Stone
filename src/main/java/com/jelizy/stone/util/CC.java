package com.jelizy.stone.util;

import org.bukkit.ChatColor;

public class CC {

  public static final String MENU_BAR;
  public static final String CHAT_BAR;
  public static final String SB_BAR;

  public static String translate(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }

  static {
    MENU_BAR =
        ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";
    CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString()
        + "------------------------------------------------";
    SB_BAR =
        ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "----------------------";
  }

  public static String translateBoolean(boolean b, boolean uppercase) {
    return b ? (uppercase ? "Tak" : "tak") : (uppercase ? "Nie" : "nie");
  }
}
