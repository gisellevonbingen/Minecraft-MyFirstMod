package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.common.ore.ProcessState;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider
{
	public ItemTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, MyFirstMod.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		for (OreType oreType : OreType.values())
		{
			for (OreState oreState : OreState.values())
			{
				if (oreState.getProcessState() == ProcessState.PROCESSING)
				{
					Item item = oreState.getItem(oreType);
					this.tag(oreState.getCategoryTag()).add(item);
					this.tag(oreState.getProcessingTag(oreType)).add(item);
				}

			}

		}

	}

}
