package com.lothrazar.cyclicmagic.item;

import com.lothrazar.cyclicmagic.IHasConfig;
import com.lothrazar.cyclicmagic.IHasRecipe;
import com.lothrazar.cyclicmagic.registry.ItemRegistry;
import com.lothrazar.cyclicmagic.util.Const;
import com.lothrazar.cyclicmagic.util.UtilPlaceBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemToolRotate extends BaseTool implements IHasRecipe ,IHasConfig{
private static final int durability = 8000;
	
	public ItemToolRotate(){
		super(durability);
	} 
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World worldObj, BlockPos pos, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {

		if (pos == null || worldObj.getBlockState(pos) == null || side == null) {
			return super.onItemUse(stack, player, worldObj, pos, hand, side, hitX, hitY, hitZ);
		}

		IBlockState clicked = worldObj.getBlockState(pos);
		if (clicked.getBlock() == null) {
			//cancelled
			return super.onItemUse(stack, player, worldObj, pos, hand, side, hitX, hitY, hitZ);
		}

		UtilPlaceBlocks.rotateBlockValidState(pos, worldObj, side, player);

		super.onUse(stack, player, worldObj, hand);
		return super.onItemUse(stack, player, worldObj, pos, hand, side, hitX, hitY, hitZ);// EnumActionResult.PASS;
	}

	@Override
	public void syncConfig(Configuration config) {

//		Property prop = config.get(Const.ConfigCategory.items, "ToolRotate", true, "Tool that can rotate almost anything");
//		prop.setRequiresMcRestart(true); 
//		ItemRegistry.setConfigMap(this,prop.getBoolean());
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ItemStack(this, 8), 
				" dr", 
				" bd", 
				"b  ", 
			'b',Items.BLAZE_ROD, 
		    'd',Items.DIAMOND,
		    'r',Blocks.LAPIS_BLOCK); 
	}

}
