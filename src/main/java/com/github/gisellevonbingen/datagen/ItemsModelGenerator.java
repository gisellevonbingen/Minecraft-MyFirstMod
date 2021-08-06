package com.github.gisellevonbingen.datagen;

import java.lang.reflect.Field;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.material.MaterialState;
import com.github.gisellevonbingen.common.material.MaterialType;
import com.github.gisellevonbingen.util.UnsafeHacks;

import mekanism.common.Mekanism;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import sun.misc.Unsafe;

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
		long offset = 0L;
		Unsafe unsafe = UnsafeHacks.UNSAFE;

		try
		{
			try
			{
				Field enableField = ExistingFileHelper.class.getDeclaredField("enable");
				enableField.setAccessible(true);
				offset = unsafe.objectFieldOffset(enableField);
				unsafe.putBoolean(this.existingFileHelper, offset, false);

				this.onRegisterModels();
			}
			catch (NoSuchFieldException | SecurityException e)
			{
				e.printStackTrace();
			}

		}
		finally
		{
			try
			{
				if (offset != 0)
				{
					unsafe.putBoolean(this.existingFileHelper, offset, enable);
				}

			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}

		}

	}

	private void onRegisterModels()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : MaterialState.values())
			{
				if (materialState != MaterialState.ORE)
				{
					Item item = materialState.getItem(materialType);
					this.singleTexture(item.getRegistryName().getPath(), this.mcLoc("item/generated"), "layer0", new ResourceLocation(Mekanism.MODID, "item/" + materialState.getBaseName()));
				}

			}

		}

	}

}
