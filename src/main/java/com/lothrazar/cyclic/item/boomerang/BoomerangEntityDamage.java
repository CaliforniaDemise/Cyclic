package com.lothrazar.cyclic.item.boomerang;

import com.lothrazar.cyclic.item.boomerang.BoomerangItem.Boomer;
import com.lothrazar.cyclic.registry.EntityRegistry;
import com.lothrazar.cyclic.registry.ItemRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BoomerangEntityDamage extends BoomerangEntity {

  public BoomerangEntityDamage(EntityType<? extends ThrowableItemProjectile> type, Level worldIn) {
    super(type, worldIn);
    boomerangType = Boomer.DAMAGE;
  }

  public BoomerangEntityDamage(Level worldIn, LivingEntity throwerIn) {
    super(EntityRegistry.boomerang_damage, throwerIn, worldIn);
    boomerangType = Boomer.DAMAGE;
  }

  @Override
  protected Item getDefaultItem() {
    return ItemRegistry.BOOMERANG_DAMAGE.get();
  }
}
