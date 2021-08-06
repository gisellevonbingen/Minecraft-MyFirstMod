package com.github.gisellevonbingen.common.item;

import com.github.gisellevonbingen.common.MyFirstModItemGroups;
import com.github.gisellevonbingen.common.material.MaterialState;
import com.github.gisellevonbingen.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemStatedMaterial extends Item
{
	private final MaterialType materialType;
	private final MaterialState materialState;

	public ItemStatedMaterial(MaterialType materialType, MaterialState materialState)
	{
		super(new Properties().tab(MyFirstModItemGroups.tabMyFirstMod));

		this.materialType = materialType;
		this.materialState = materialState;
	}

	@Override
	public ITextComponent getName(ItemStack itemStack)
	{
		String descriptionId = this.getDescriptionId(itemStack);

		if (LanguageMap.getInstance().has(descriptionId) == true)
		{
			return new TranslationTextComponent(descriptionId);
		}
		else
		{
			TranslationTextComponent materialType = new TranslationTextComponent(this.materialType.getDescriptionId());
			return new TranslationTextComponent(this.materialState.getStatedDescriptionId(), materialType);
		}

	}

	public MaterialType getOreType()
	{
		return this.materialType;
	}

	public MaterialState getOreState()
	{
		return this.materialState;
	}

}
