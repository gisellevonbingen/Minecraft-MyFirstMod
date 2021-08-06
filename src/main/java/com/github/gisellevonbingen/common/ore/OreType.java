package com.github.gisellevonbingen.common.ore;

public enum OreType
{
	Cobalt("cobalt", 0x2376DD),
	Unknownium("unknownium", 0xFFFFFF),;

	private String oreName;
	private int color;

	private OreType(String oreName, int color)
	{
		this.oreName = oreName;
		this.color = 0xFF000000 | color;
	}

	public String getOreName()
	{
		return this.oreName;
	}

	public int getColor()
	{
		return this.color;
	}

}
