package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MyFirstModItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MyFirstMod.MODID);

	public static void register()
	{
		register(REGISTER);
	}

	public static void register(DeferredRegister<Item> register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

}
