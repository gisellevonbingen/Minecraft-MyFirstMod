package com.github.gisellevonbingen.common.ore;

import java.util.List;

import javax.annotation.Nullable;

import mekanism.common.Mekanism;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public enum OreState
{
	ORE("minecraft", "ores"),
	DUST(Mekanism.MODID, "dusts"),
	DIRTY_DUST(Mekanism.MODID, "dustDirtys"),
	CLUMP(Mekanism.MODID, "clumps"),
	SHARD(Mekanism.MODID, "shards"),
	CRYSTAL(Mekanism.MODID, "crystals"),;

	private String modId;
	private String tagPrefix;

	OreState(String modId, String tagPrefix)
	{
		this.modId = modId;
		this.tagPrefix = tagPrefix;
	}

	public String getModId()
	{
		return modId;
	}

	public ResourceLocation getTag(String oreName)
	{
		return new ResourceLocation(this.modId, this.tagPrefix + "/" + oreName);
	}

	@Nullable
	public Item getItem(String oreName)
	{
		List<Item> items = ItemTags.bind(this.getTag(oreName).toString()).getValues();
		return items.stream().findAny().get();
	}

}
