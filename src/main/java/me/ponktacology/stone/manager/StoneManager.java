package me.ponktacology.stone.manager;

import me.ponktacology.stone.generator.Generator;
import java.util.HashMap;
import lombok.Getter;
import org.bukkit.Location;

public final class StoneManager {

  @Getter
  private static final HashMap<Location, Generator> generators = new HashMap<>();
}
