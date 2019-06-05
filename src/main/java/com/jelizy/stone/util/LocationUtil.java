package com.jelizy.stone.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

  public static String serialize(Location location) {
    StringBuilder sb = new StringBuilder();
    sb.append(location.getWorld().getName());
    sb.append("@");
    sb.append(location.getBlockX());
    sb.append("@");
    sb.append(location.getBlockY());
    sb.append("@");
    sb.append(location.getBlockZ());
    return sb.toString();
  }

  public static Location deserialize(String location) {
    String[] loc = location.split("@");
    return new Location(Bukkit.getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]));
  }

}
