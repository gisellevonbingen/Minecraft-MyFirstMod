package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsModelGenerator extends ItemModelProvider
{
	public ItemsModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, MyFirstMod.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		this.singleTexture(Registration.ROCK.get().getRegistryName().getPath(), this.mcLoc("item/generated"), "layer0", this.modLoc("item/rock"));
	}

}
