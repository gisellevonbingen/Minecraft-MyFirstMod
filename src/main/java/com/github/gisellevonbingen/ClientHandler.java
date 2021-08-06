package com.github.gisellevonbingen;

import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.client.renderer.color.CommonItemColor;
import com.github.gisellevonbingen.common.MyFirstModItems;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientHandler
{
	public ClientHandler()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(this);
	}

	@SubscribeEvent
	public void registerItemColors(ColorHandlerEvent.Item event)
	{
		for (Entry<OreType, Map<OreState, RegistryObject<Item>>> entry : MyFirstModItems.PROCESSING_ITEMS.entrySet())
		{
			CommonItemColor itemColor = new CommonItemColor(entry.getKey());

			for (RegistryObject<Item> item : entry.getValue().values())
			{
				event.getItemColors().register(itemColor, item.get());
			}

		}

	}

}
