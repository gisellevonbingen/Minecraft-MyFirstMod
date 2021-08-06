package com.github.gisellevonbingen.datagen;

import java.lang.reflect.Field;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.util.UnsafeHelper;

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
		boolean enable = this.existingFileHelper.isEnabled();
		Field enableField = null;

		try
		{
			try
			{
				enableField = ExistingFileHelper.class.getDeclaredField("enable");
				enableField.setAccessible(true);
			}
			catch (NoSuchFieldException | SecurityException e)
			{
				e.printStackTrace();
			}

			if (enableField != null)
			{
				UnsafeHelper.putBoolean(this.existingFileHelper, enableField, false);
			}

			this.onRegisterModels();
		}
		finally
		{
			if (enableField != null)
			{
				UnsafeHelper.putBoolean(this.existingFileHelper, enableField, enable);
			}

		}

	}

	private void onRegisterModels()
	{

	}

}
