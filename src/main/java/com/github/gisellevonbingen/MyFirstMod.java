package com.github.gisellevonbingen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gisellevonbingen.common.ModSetup;
import com.github.gisellevonbingen.common.MyFirstModItems;
import com.github.gisellevonbingen.datagen.DataGenerators;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MyFirstMod.MODID)
public class MyFirstMod
{
	public static final String MODID = "myfirstmod";
	public static final Logger LOGGER = LogManager.getLogger();

	public MyFirstMod()
	{
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::new);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(ModSetup::init);
		modEventBus.register(this);
		modEventBus.register(new DataGenerators());

		MyFirstModItems.register();
	}

}
