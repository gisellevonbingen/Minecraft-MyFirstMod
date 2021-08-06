package com.github.gisellevonbingen.common.ore;

import org.apache.commons.lang3.NotImplementedException;

import com.github.gisellevonbingen.common.MyFirstModItems;

import mekanism.common.tags.MekanismTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public enum OreState
{
	ORE(Tags.Items.ORES),
	DUST(Tags.Items.DUSTS),
	DIRTY_DUST(MekanismTags.Items.DIRTY_DUSTS),
	CLUMP(MekanismTags.Items.CLUMPS),
	SHARD(MekanismTags.Items.SHARDS),
	CRYSTAL(MekanismTags.Items.CRYSTALS),
	INGOT(Tags.Items.INGOTS),
	NUGGET(Tags.Items.NUGGETS),;

	private ITag.INamedTag<Item> categoryTag;

	OreState(ITag.INamedTag<Item> categoryTag)
	{
		this.categoryTag = categoryTag;
	}

	public INamedTag<Item> getCategoryTag()
	{
		return this.categoryTag;
	}

	public ResourceLocation getStateTagName(OreType oreType)
	{
		ResourceLocation categoryTagName = this.getCategoryTag().getName();
		return new ResourceLocation(categoryTagName.getNamespace(), categoryTagName.getPath() + "/" + oreType.getOreName());
	}

	public INamedTag<Item> getStateTag(OreType oreType)
	{
		if (this != OreState.ORE)
		{
			return com.github.gisellevonbingen.common.tag.MyFirstModTags.getProcessingItemTag(oreType, this);
		}
		else
		{
			ITagCollection<Item> allTags = ItemTags.getAllTags();
			ResourceLocation tagName = this.getStateTagName(oreType);
			INamedTag<Item> tag = (INamedTag<Item>) allTags.getTag(tagName);

			if (tag != null)
			{
				return tag;
			}
			else
			{
				return ItemTags.bind(tagName.toString());
			}

		}

	}

	public String getItemName(OreType oreType)
	{
		String stateName = this.name().toLowerCase();
		String oreName = oreType.getOreName().toLowerCase();

		if (this == INGOT || this == NUGGET)
		{
			return oreName + "_" + stateName;
		}
		else
		{
			return stateName + "_" + oreName;
		}

	}

	public Item getItem(OreType oreType)
	{
		if (this != OreState.ORE)
		{
			return MyFirstModItems.getProcessingItem(oreType, this).get();
		}
		else
		{
			throw new NotImplementedException("getItem(" + oreType.name() + ")");
		}

	}

	public ItemStack getItemStack(OreType oreType)
	{
		return new ItemStack(this.getItem(oreType));
	}

	public ItemStack getItemStack(OreType oreType, int count)
	{
		return new ItemStack(this.getItem(oreType), count);
	}

}
