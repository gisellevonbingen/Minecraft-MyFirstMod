package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.common.ore.ProcessState;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
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
		for (OreType oreType : OreType.values())
		{
			for (OreState oreState : OreState.values())
			{
				if (oreState.getProcessState() == ProcessState.PROCESSING)
				{
					Item item = oreState.getItem(oreType);
					this.singleTexture(item.getRegistryName().getPath(), this.mcLoc("item/generated"), "layer0", this.modLoc("item/" + oreState.name().toLowerCase()));
				}

			}

		}

	}

}
