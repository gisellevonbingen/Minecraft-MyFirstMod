package com.github.gisellevonbingen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.gisellevonbingen.common.ModSetup;
import com.github.gisellevonbingen.common.Registration;
import com.github.gisellevonbingen.datagen.DataGenerators;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MyFirstMod.MODID)
public class MyFirstMod
{
	public static final String MODID = "myfirstmod";
	public static final Logger LOGGER = LogManager.getLogger();

	public MyFirstMod()
	{
		Registration.init();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
		FMLJavaModLoadingContext.get().getModEventBus().register(new DataGenerators());

	}

}
