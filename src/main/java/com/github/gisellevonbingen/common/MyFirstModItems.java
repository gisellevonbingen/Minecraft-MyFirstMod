package com.github.gisellevonbingen.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.common.ore.ProcessState;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.IForgeRegistry;

public class MyFirstModItems
{
	public static final Map<OreType, Map<OreState, Item>> PROCESSING_ITEMS = new HashMap<>();

	public static Item getProcessingItem(OreType oreType, OreState oreState)
	{
		Map<OreState, Item> map = PROCESSING_ITEMS.get(oreType);
		return map != null ? map.get(oreState) : null;
	}

	public static Item[] getProcessingItems(OreState oreState)
	{
		List<Item> list = new ArrayList<>();

		for (Entry<OreType, Map<OreState, Item>> entry : PROCESSING_ITEMS.entrySet())
		{
			Item item = entry.getValue().get(oreState);

			if (item != null)
			{
				list.add(item);
			}

		}

		return list.toArray(new Item[0]);
	}

	public static void initialize(IForgeRegistry<Item> registry)
	{
		for (OreType oreType : OreType.values())
		{
			Map<OreState, Item> map2 = new HashMap<>();
			PROCESSING_ITEMS.put(oreType, map2);

			for (OreState oreState : OreState.values())
			{
				if (oreState.getProcessState() == ProcessState.PROCESSING)
				{
					String itemName = oreState.getItemName(oreType);
					Item item = new Item(new Properties().tab(ItemGroup.TAB_MISC));
					item.setRegistryName(MyFirstMod.MODID, itemName);

					map2.put(oreState, item);
					registry.register(item);
				}

			}

		}

	}

}
