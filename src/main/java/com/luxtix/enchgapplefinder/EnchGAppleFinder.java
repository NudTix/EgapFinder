package com.luxtix.enchgapplefinder;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "enchgapplefinder", name = "Enchanted Gold Apple Finder", version = "1.0", acceptedMinecraftVersions = "[1.12.2]")
public class EnchGAppleFinder {
  public static final String MODID = "enchgapplefinder";
  
  public static final String NAME = "Enchanted Gold Apple Finder";
  
  public static final String VERSION = "1.0";
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {}
  
  @EventHandler
  public void init(FMLInitializationEvent event) {}
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    ChestDetection.writeToFile("Start of new MC instance");
  }
}
