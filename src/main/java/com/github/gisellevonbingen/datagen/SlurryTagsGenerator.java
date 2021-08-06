package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.MyFirstModSlurries;
import com.github.gisellevonbingen.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.tag.ChemicalTagsProvider.SlurryTagsProvider;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlurryTagsGenerator extends SlurryTagsProvider
{
	protected SlurryTagsGenerator(DataGenerator gen, ExistingFileHelper existingFileHelper)
	{
		super(gen, MyFirstMod.MODID, existingFileHelper);
	}

	@Override
	public String getName()
	{
		return "Slurry Tags";
	}

	@Override
	protected void addTags()
	{
		Builder<Slurry> dirty = this.tag(MekanismTags.Slurries.DIRTY);
		Builder<Slurry> clean = this.tag(MekanismTags.Slurries.CLEAN);

		for (MaterialType materialType : MaterialType.values())
		{
			SlurryRegistryObject<Slurry, Slurry> registry = MyFirstModSlurries.getSlurryRegistry(materialType);
			dirty.add(registry.getDirtySlurry());
			clean.add(registry.getCleanSlurry());
		}

	}

}
