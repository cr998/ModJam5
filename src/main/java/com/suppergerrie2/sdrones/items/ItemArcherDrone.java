package com.suppergerrie2.sdrones.items;

import com.suppergerrie2.sdrones.entities.EntityArcherDrone;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemArcherDrone extends ItemDrone {

	public ItemArcherDrone(String name) {
		super(name);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack itemstack = player.getHeldItem(hand);

		if (!player.capabilities.isCreativeMode)
		{
			itemstack.shrink(1);
		}

		if (!worldIn.isRemote)
		{		
			ItemStack stack = itemstack.copy();
			stack.setCount(1);
			EntityArcherDrone entitydrone = new EntityArcherDrone(worldIn, pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ, stack, facing);
			
			if(this.hasFilter(stack)) {
				entitydrone.setFilter(this.getFilter(itemstack));
			}
			
			worldIn.spawnEntity(entitydrone);
		}

		return EnumActionResult.SUCCESS;
	}
}
