package com.github.gisellevonbingen.client.renderer.color;

import com.github.gisellevonbingen.common.ore.OreType;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class CommonItemColor implements IItemColor
{
	private OreType type;

	public CommonItemColor(OreType type)
	{
		this.type = type;
	}

	@Override
	public int getColor(ItemStack itemColor, int p1)
	{
		return 0xFF000000 | this.type.getColor();
	}

	public OreType getType()
	{
		return this.type;
	}

}
