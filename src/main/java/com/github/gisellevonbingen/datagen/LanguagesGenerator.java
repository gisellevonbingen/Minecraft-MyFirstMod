package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguagesGenerator extends LanguageProvider
{
	public LanguagesGenerator(DataGenerator generator)
	{
		super(generator, MyFirstMod.MODID, "en_us");
	}

	@Override
	protected void addTranslations()
	{

	}

}
