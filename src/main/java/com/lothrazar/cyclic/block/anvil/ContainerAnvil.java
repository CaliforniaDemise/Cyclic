package com.lothrazar.cyclic.block.anvil;

import com.lothrazar.cyclic.base.ContainerBase;
import com.lothrazar.cyclic.registry.BlockRegistry;
import com.lothrazar.cyclic.registry.ContainerScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAnvil extends ContainerBase {

  TileAnvilAuto tile;

  public ContainerAnvil(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
    super(ContainerScreenRegistry.anvil, windowId);
    tile = (TileAnvilAuto) world.getTileEntity(pos);
    this.playerEntity = player;
    this.playerInventory = playerInventory;
    tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
      this.endInv = h.getSlots();
      addSlot(new SlotItemHandler(h, 0, 55, 35));
      addSlot(new SlotItemHandler(h, 1, 109, 35));
    });
    layoutPlayerInventorySlots(8, 84);
    this.trackAllIntFields(tile, TileAnvilAuto.Fields.values().length);
    trackEnergy(tile);
  }

  @Override
  public boolean canInteractWith(PlayerEntity playerIn) {
    return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), playerEntity, BlockRegistry.anvil);
  }
}