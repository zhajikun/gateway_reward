package com.gateway.rewards.utils;

import java.util.UUID;
import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Component;

@Component
public class UUIDgenerator {

  public static String generateRandomUUID() {
    UUID uuid = Generators.randomBasedGenerator().generate();
    return uuid.toString();
  }

  public static String generateTimeBaseUUID() {
    UUID uuid = Generators.timeBasedGenerator().generate();
    return uuid.toString();
  }
}
