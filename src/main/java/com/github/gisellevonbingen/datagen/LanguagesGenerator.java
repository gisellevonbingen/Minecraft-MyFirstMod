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
		String statedPrefix = MaterialState.DescriptionKey_Stated + ".";
		this.add(statedPrefix + "_comment_0", "");
		this.add(statedPrefix + "_comment_1", "===== ItemStatedMaterial Common Rule =====");
		this.add(statedPrefix + "_comment_2", "");
		this.add(statedPrefix + "_comment_3", "%s is material type (e.g. Cobalt, Silver)");
		this.add(statedPrefix + "_comment_4", "    from materialType.xxxxx");
		this.add(statedPrefix + "_comment_5", "");
		this.add(statedPrefix + "_comment_6", "Items can override this rule thought by declare that item's translation");
		this.add(statedPrefix + "_comment_7", "    e.g.) \"item.cobalt_ingot\" : \"Blue Metal\"");

		this.add(statedPrefix + MaterialState.ORE.getBaseName(), "%s Ore");
		this.add(statedPrefix + MaterialState.DUST.getBaseName(), "%s Dust");
		this.add(statedPrefix + MaterialState.DIRTY_DUST.getBaseName(), "Dirty %s Dust");
		this.add(statedPrefix + MaterialState.CLUMP.getBaseName(), "%s Clump");
		this.add(statedPrefix + MaterialState.SHARD.getBaseName(), "%s Shard");
		this.add(statedPrefix + MaterialState.CRYSTAL.getBaseName(), "%s Crystal");
		this.add(statedPrefix + MaterialState.INGOT.getBaseName(), "%s Ingot");
		this.add(statedPrefix + MaterialState.NUGGET.getBaseName(), "%s Nugget");

		this.add("materialType._comment_0", "");
		this.add("materialType._comment_1", "===== Material Type =====");
		this.add("materialType._comment_2", "");

		for (MaterialType materialType : MaterialType.values())
		{
			this.add("materialType." + materialType.name().toLowerCase(), materialType.getDisplayName());
		}

	}

}
