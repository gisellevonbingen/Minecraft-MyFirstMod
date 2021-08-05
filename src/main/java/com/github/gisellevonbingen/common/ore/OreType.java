package com.github.gisellevonbingen.common.ore;

public enum OreType
{
	Cobalt("tconstruct", "cobalt"),
	Unknownium(null, "unknownium"),;

	private String modId;
	private String oreName;

	private OreType(String modId, String oreName)
	{
		this.modId = modId;
		this.oreName = oreName;
	}
	
	public String getModId()
	{
		return this.modId;
	}

	public String getOreName()
	{
		return this.oreName;
	}

}
