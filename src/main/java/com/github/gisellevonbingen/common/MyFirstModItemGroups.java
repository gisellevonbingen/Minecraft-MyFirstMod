package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MyFirstModItemGroups
{
	public static final ItemGroup tabMyFirstMod = new ItemGroup(MyFirstMod.MODID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return Items.DIAMOND.getDefaultInstance();
		}

	};

}
