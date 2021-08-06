package com.github.gisellevonbingen.common.material;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public enum MaterialType
{
	Cobalt(MaterialResultShape.INGOT, "cobalt", "Cobalt", 0x1E66BF),
	Silver(MaterialResultShape.INGOT, "silver", "Silver", 0xD8E4ED),
	Nickel(MaterialResultShape.INGOT, "nickel", "Nickel", 0xE5E09E),
	Unknownium(MaterialResultShape.INGOT, "unknownium", "Unknownium", 0xFFFFFF),;

	private final MaterialResultShape resultShape;
	private final String baseName;
	private final String displayName;
	private final int color;

	private MaterialType(MaterialResultShape resultShape, String baseName, String displayName, int color)
	{
		this.resultShape = resultShape;
		this.baseName = baseName;
		this.displayName = displayName;
		this.color = 0xFF000000 | color;
	}

	public String getDescriptionId()
	{
		return makeDescriptionId(this.baseName);
	}

	public static String makeDescriptionId(String baseName)
	{
		return Util.makeDescriptionId("materialType", new ResourceLocation(MyFirstMod.MODID, baseName));
	}

	public MaterialResultShape getResultShape()
	{
		return this.resultShape;
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
