package com.jelizy.stone.manager;

import com.jelizy.stone.generator.Generator;
import java.util.HashMap;
import lombok.Getter;
import org.bukkit.Location;

public final class StoneManager {

  @Getter
  private static final HashMap<Location, Generator> generators = new HashMap<>();
}
