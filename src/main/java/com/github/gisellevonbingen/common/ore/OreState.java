package com.github.gisellevonbingen.common.ore;

import com.github.gisellevonbingen.common.Registration;

import mekanism.common.tags.MekanismTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryManager;

public enum OreState
{
	ORE(Tags.Items.ORES, ProcessState.ORE),
	DUST(Tags.Items.DUSTS),
	DIRTY_DUST(MekanismTags.Items.DIRTY_DUSTS),
	CLUMP(MekanismTags.Items.CLUMPS),
	SHARD(MekanismTags.Items.SHARDS),
	CRYSTAL(MekanismTags.Items.CRYSTALS),
	INGOT(Tags.Items.INGOTS, ProcessState.INGOT);

	private ITag.INamedTag<Item> categoryTag;
	private ProcessState processState;

	OreState(ITag.INamedTag<Item> categoryTag)
	{
		this(categoryTag, ProcessState.PROCESSING);
	}

	OreState(ITag.INamedTag<Item> categoryTag, ProcessState processState)
	{
		this.categoryTag = categoryTag;
		this.processState = processState;
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
		if (this.processState == ProcessState.PROCESSING)
		{
			return com.github.gisellevonbingen.common.tag.Tags.getProcessingItemTag(oreType, this);
		}
		else
		{
			return (INamedTag<Item>) ItemTags.getAllTags().getTag(this.getStateTagName(oreType));
		}

	}

	public ProcessState getProcessState()
	{
		return this.processState;
	}

	public String getItemName(OreType oreType)
	{
		return oreType.getOreName().toLowerCase() + "_" + this.name().toLowerCase();
	}

	public Item getItem(OreType oreType)
	{
		if (this.processState == ProcessState.PROCESSING)
		{
			return Registration.getProcessingItem(oreType, this);
		}
		else
		{
			String itemName = this.getItemName(oreType);
			return RegistryManager.ACTIVE.getRegistry(Item.class).getValue(new ResourceLocation(oreType.getModId(), itemName));
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
