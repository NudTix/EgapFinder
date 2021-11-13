/*    */ package com.luxtix.enchgapplefinder;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityMinecartContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Enchantments;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityLockableLoot;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class ChestDetection {
/*    */   @SubscribeEvent
/*    */   public static void WorldTick(TickEvent.WorldTickEvent event) {
/* 27 */     if (event.world.isRemote)
/*    */       return; 
/* 29 */     World world = event.world;
/* 30 */     EntityPlayer player = null;
/* 31 */     if (!world.playerEntities.isEmpty()) {
/* 32 */       player = world.playerEntities.get(0);
/* 36 */       for (TileEntity tile : world.loadedTileEntityList) {
/* 37 */         if (tile instanceof TileEntityLockableLoot) {
/* 38 */           TileEntityLockableLoot lockable = (TileEntityLockableLoot)tile;
/* 39 */           if (lockable.getLootTable() != null) {
/* 40 */             lockable.fillWithLoot(player);
/* 41 */             for (int i = 0; i < lockable.getSizeInventory(); i++) {
/* 42 */               ItemStack stack = lockable.getStackInSlot(i);
/* 43 */               if (stack.getItem() == Items.GOLDEN_APPLE && stack.getItemDamage() == 1)
/* 44 */                 writeToFile("Dungeon Chest with ench gapple at: " + lockable.getPos().getX() + " " + lockable.getPos().getY() + " " + lockable.getPos().getZ()); 
/* 46 */               if (stack.getItem() == Items.ENCHANTED_BOOK && 
/* 47 */                 EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, stack) > 0)
/* 48 */                 writeToFile("Dungeon Chest with Mending Book: " + lockable.getPos().getX() + " " + lockable.getPos().getY() + " " + lockable.getPos().getZ()); 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/* 60 */       for (Entity entity : world.loadedEntityList) {
/* 61 */         if (entity instanceof EntityMinecartContainer) {
/* 62 */           EntityMinecartContainer cart = (EntityMinecartContainer)entity;
/* 63 */           if (cart.getLootTable() != null) {
/* 64 */             cart.addLoot(player);
/* 65 */             for (int i = 0; i < cart.itemHandler.getSlots(); i++) {
/* 66 */               ItemStack stack = cart.itemHandler.getStackInSlot(i);
/* 67 */               if (stack.getItem() == Items.GOLDEN_APPLE && stack.getItemDamage() == 1)
/* 68 */                 writeToFile("Minecart with ench gapple at: " + cart.posX + " " + cart.posY + " " + cart.posZ); 
/* 70 */               if (stack.getItem() == Items.ENCHANTED_BOOK && 
/* 71 */                 EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, stack) > 0)
/* 72 */                 writeToFile("Minecart with Mending at: " + cart.posX + " " + cart.posY + " " + cart.posZ); 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected static void writeToFile(String coords) {
/* 84 */     try(FileWriter fw = new FileWriter("LuxTix_egaps.txt", true);
/* 85 */         BufferedWriter bw = new BufferedWriter(fw); 
/* 86 */         PrintWriter out = new PrintWriter(bw)) {
/* 87 */       out.println(coords);
/* 88 */     } catch (IOException iOException) {}
/*    */   }
/*    */ }


