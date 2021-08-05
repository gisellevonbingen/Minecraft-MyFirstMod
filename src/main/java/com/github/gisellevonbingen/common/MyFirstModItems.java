package com.github.gisellevonbingen.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MyFirstModItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MyFirstMod.MODID);
	public static final Map<OreType, Map<OreState, RegistryObject<Item>>> PROCESSING_ITEMS = new HashMap<>();

	public static RegistryObject<Item> getProcessingItem(OreType oreType, OreState oreState)
	{
		Map<OreState, RegistryObject<Item>> map = PROCESSING_ITEMS.get(oreType);
		return map != null ? map.get(oreState) : null;
	}

	public static List<RegistryObject<Item>> getProcessingItems(OreState oreState)
	{
		List<RegistryObject<Item>> list = new ArrayList<>();

		for (Entry<OreType, Map<OreState, RegistryObject<Item>>> entry : PROCESSING_ITEMS.entrySet())
		{
			RegistryObject<Item> registryObject = entry.getValue().get(oreState);

			if (registryObject != null)
			{
				list.add(registryObject);
			}

		}

		return list;
	}

	public static void register()
	{
		register(REGISTER);
	}

	public static void register(DeferredRegister<Item> register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

		for (OreType oreType : OreType.values())
		{
			registerOreType(register, oreType);
		}

	}

	public static void registerOreType(DeferredRegister<Item> registry, OreType oreType)
	{
		Map<OreState, RegistryObject<Item>> map2 = new HashMap<>();
		PROCESSING_ITEMS.put(oreType, map2);

		for (OreState oreState : OreState.values())
		{
			if (oreState != OreState.ORE)
			{
				RegistryObject<Item> registryObject = registry.register(oreState.getItemName(oreType), () -> new Item(new Properties().tab(MyFirstModItemGroups.tabMyFirstMod)));
				map2.put(oreState, registryObject);
			}

		}

	}

}
