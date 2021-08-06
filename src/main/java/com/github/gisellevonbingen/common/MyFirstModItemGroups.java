package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MyFirstModItemGroups
{
	public static final ItemGroup tabMyFirstMod = new ItemGroup(MyFirstMod.MODID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return OreState.CRYSTAL.getItemStack(OreType.Cobalt);
		}

	};

}
