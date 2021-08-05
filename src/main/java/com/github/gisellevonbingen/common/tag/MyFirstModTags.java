package com.github.gisellevonbingen.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class MyFirstModTags
{
	public static final Map<OreType, Map<OreState, ITag.INamedTag<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

	static
	{
		initialize();
	}

	public static ITag.INamedTag<Item> getProcessingItemTag(OreType oreType, OreState oreState)
	{
		Map<OreState, ITag.INamedTag<Item>> map = PROCESSING_ITEM_TAGS.get(oreType);
		return map != null ? map.get(oreState) : null;
	}

	public static void initialize()
	{
		for (OreType oreType : OreType.values())
		{
			Map<OreState, ITag.INamedTag<Item>> map2 = new HashMap<>();
			PROCESSING_ITEM_TAGS.put(oreType, map2);

			for (OreState oreState : OreState.values())
			{
				if (oreState != OreState.ORE)
				{
					INamedTag<Item> tag = tag(oreType, oreState);
					map2.put(oreState, tag);
				}

			}

		}

	}

	private static ITag.INamedTag<Item> tag(OreType oreType, OreState oreState)
	{
		return ItemTags.bind(oreState.getStateTagName(oreType).toString());
	}

}
