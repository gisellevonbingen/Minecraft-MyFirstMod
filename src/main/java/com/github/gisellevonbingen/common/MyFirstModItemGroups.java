package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.material.MaterialState;
import com.github.gisellevonbingen.common.material.MaterialType;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MyFirstModItemGroups
{
	public static final ItemGroup tabMyFirstMod = new ItemGroup(MyFirstMod.MODID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return MaterialState.CRYSTAL.getItemStack(MaterialType.Cobalt);
		}

	};

}
