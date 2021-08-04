package com.github.gisellevonbingen.common.ore;

public enum OreType
{
	Cobalt("cobalt"),;

	private String oreName;

	private OreType(String oreName)
	{
		this.oreName = oreName;
	}

	public String getOreName()
	{
		return this.oreName;
	}

}
