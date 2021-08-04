package com.github.gisellevonbingen.common;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MyFirstMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup
{
	public static void init(final FMLCommonSetupEvent event)
	{

	}

}
