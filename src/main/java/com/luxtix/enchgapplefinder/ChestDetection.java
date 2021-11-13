package com.luxtix.enchgapplefinder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@EventBusSubscriber
public class ChestDetection {
  @SubscribeEvent
  public static void WorldTick(TickEvent.WorldTickEvent event) {
    if (event.world.field_72995_K)
      return; 
    World world = event.world;
    EntityPlayer player = null;
    if (!world.field_73010_i.isEmpty()) {
      player = world.field_73010_i.get(0);
      for (TileEntity tile : world.field_147482_g) {
        if (tile instanceof TileEntityLockableLoot) {
          TileEntityLockableLoot lockable = (TileEntityLockableLoot)tile;
          if (lockable.func_184276_b() != null) {
            lockable.func_184281_d(player);
            for (int i = 0; i < lockable.func_70302_i_(); i++) {
              ItemStack stack = lockable.func_70301_a(i);
              if (stack.func_77973_b() == Items.field_151153_ao && stack.func_77952_i() == 1)
                writeToFile("Dungeon Chest with ench gapple at: " + lockable.func_174877_v().func_177958_n() + " " + lockable.func_174877_v().func_177956_o() + " " + lockable.func_174877_v().func_177952_p()); 
              if (stack.func_77973_b() == Items.field_151134_bR && 
                EnchantmentHelper.func_77506_a(Enchantments.field_185296_A, stack) > 0)
                writeToFile("Dungeon Chest with Mending Book: " + lockable.func_174877_v().func_177958_n() + " " + lockable.func_174877_v().func_177956_o() + " " + lockable.func_174877_v().func_177952_p()); 
            } 
          } 
        } 
      } 
      for (Entity entity : world.field_72996_f) {
        if (entity instanceof EntityMinecartContainer) {
          EntityMinecartContainer cart = (EntityMinecartContainer)entity;
          if (cart.func_184276_b() != null) {
            cart.func_184288_f(player);
            for (int i = 0; i < cart.itemHandler.getSlots(); i++) {
              ItemStack stack = cart.itemHandler.getStackInSlot(i);
              if (stack.func_77973_b() == Items.field_151153_ao && stack.func_77952_i() == 1)
                writeToFile("Minecart with ench gapple at: " + cart.field_70165_t + " " + cart.field_70163_u + " " + cart.field_70161_v); 
              if (stack.func_77973_b() == Items.field_151134_bR && 
                EnchantmentHelper.func_77506_a(Enchantments.field_185296_A, stack) > 0)
                writeToFile("Minecart with Mending at: " + cart.field_70165_t + " " + cart.field_70163_u + " " + cart.field_70161_v); 
            } 
          } 
        } 
      } 
    } 
  }
  
  protected static void writeToFile(String coords) {
    try(FileWriter fw = new FileWriter("LuxTix_egaps.txt", true); 
        BufferedWriter bw = new BufferedWriter(fw); 
        PrintWriter out = new PrintWriter(bw)) {
      out.println(coords);
    } catch (IOException iOException) {}
  }
}
