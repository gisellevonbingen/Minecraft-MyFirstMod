package com.github.gisellevonbingen.common.material;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public enum MaterialType
{
	Cobalt("cobalt", "Cobalt", 0x2376DD),
	Unknownium("unknownium", "Unknownium", 0xFFFFFF),;

	public static final String DescriptionKey = "materialType";

	private String baseName;
	private String displayName;
	private int color;

	private MaterialType(String baseName, String displayName, int color)
	{
		this.baseName = baseName;
		this.displayName = displayName;
		this.color = 0xFF000000 | color;
	}

	public String getDescriptionId()
	{
		return Util.makeDescriptionId(DescriptionKey, new ResourceLocation(MyFirstMod.MODID, this.baseName));
	}

	public String getBaseName()
	{
		return this.baseName;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public int getColor()
	{
		return this.color;
	}

}
