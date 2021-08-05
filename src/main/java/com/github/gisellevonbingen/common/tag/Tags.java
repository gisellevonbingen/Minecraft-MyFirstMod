package com.github.gisellevonbingen.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.common.ore.ProcessState;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class Tags
{
	public static final Map<OreType, Map<OreState, ITag.INamedTag<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

	static
	{
		initializeItemTags();
	}

	public static ITag.INamedTag<Item> getProcessingItemTag(OreType oreType, OreState oreState)
	{
		Map<OreState, ITag.INamedTag<Item>> map = PROCESSING_ITEM_TAGS.get(oreType);
		return map != null ? map.get(oreState) : null;
	}

	public static void initializeItemTags()
	{
		for (OreType oreType : OreType.values())
		{
			Map<OreState, ITag.INamedTag<Item>> map2 = new HashMap<>();
			PROCESSING_ITEM_TAGS.put(oreType, map2);

			for (OreState oreState : OreState.values())
			{
				if (oreState.getProcessState() == ProcessState.PROCESSING)
				{
					INamedTag<Item> tag = tag(oreType, oreState);
					map2.put(oreState, tag);
				}

			}

		}

	}

	private static ITag.INamedTag<Item> tag(OreType oreType, OreState oreState)
	{
		ResourceLocation categoryTagRL = oreState.getCategoryTag().getName();
		ResourceLocation oreTagRL = new ResourceLocation(categoryTagRL.getNamespace(), categoryTagRL.getPath() + "/" + oreType.getOreName());
		return ItemTags.bind(oreTagRL.toString());
	}

}
