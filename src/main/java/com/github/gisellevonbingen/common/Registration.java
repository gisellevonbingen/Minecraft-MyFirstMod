package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MyFirstMod.MODID);

	public static RegistryObject<Item> ROCK = null;

	public static void init()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ROCK = ITEMS.register("rock", () -> new Item(new Properties().tab(ItemGroup.TAB_MISC)));
	}

}
