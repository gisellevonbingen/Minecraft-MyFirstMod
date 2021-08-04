package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageGenerator extends LanguageProvider
{
	public LanguageGenerator(DataGenerator generator)
    {
        super(generator, MyFirstMod.MODID, "en_us");
    }

	@Override
	protected void addTranslations()
	{
		this.add(Registration.ROCK.get(), "ROCK?");
	}
	
}
