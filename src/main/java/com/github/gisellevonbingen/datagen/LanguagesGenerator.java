package com.github.gisellevonbingen.datagen;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.material.MaterialState;
import com.github.gisellevonbingen.common.material.MaterialType;

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
		String statedCommentPrefix = MaterialState.makeDescriptionId("_comment");
		int statedCommentLine = 0;
		this.add(statedCommentPrefix + (statedCommentLine++), "");
		this.add(statedCommentPrefix + (statedCommentLine++), "===== ItemStatedMaterial Common Rule =====");
		this.add(statedCommentPrefix + (statedCommentLine++), "");
		this.add(statedCommentPrefix + (statedCommentLine++), "%s is material type (e.g. Cobalt, Silver)");
		this.add(statedCommentPrefix + (statedCommentLine++), "    from materialType.xxxxx");
		this.add(statedCommentPrefix + (statedCommentLine++), "");
		this.add(statedCommentPrefix + (statedCommentLine++), "Items can override this rule thought by declare that item's translation");
		this.add(statedCommentPrefix + (statedCommentLine++), "    e.g.) '" + MaterialState.INGOT.getItem(MaterialType.Cobalt).getDescriptionId() + "' : 'Blue Metal'");

		this.add(MaterialState.ORE.getStatedDescriptionId(), "%s Ore");
		this.add(MaterialState.DUST.getStatedDescriptionId(), "%s Dust");
		this.add(MaterialState.DIRTY_DUST.getStatedDescriptionId(), "Dirty %s Dust");
		this.add(MaterialState.CLUMP.getStatedDescriptionId(), "%s Clump");
		this.add(MaterialState.SHARD.getStatedDescriptionId(), "%s Shard");
		this.add(MaterialState.CRYSTAL.getStatedDescriptionId(), "%s Crystal");
		this.add(MaterialState.INGOT.getStatedDescriptionId(), "%s Ingot");
		this.add(MaterialState.NUGGET.getStatedDescriptionId(), "%s Nugget");

		String materialTypeCommentPrefix = MaterialType.makeDescriptionId("_comment");
		int materialTypeCommentLine = 0;
		this.add(materialTypeCommentPrefix + (materialTypeCommentLine++), "");
		this.add(materialTypeCommentPrefix + (materialTypeCommentLine++), "===== Material Type =====");
		this.add(materialTypeCommentPrefix + (materialTypeCommentLine++), "");

		for (MaterialType materialType : MaterialType.values())
		{
			this.add(materialType.getDescriptionId(), materialType.getDisplayName());
		}

	}

}
