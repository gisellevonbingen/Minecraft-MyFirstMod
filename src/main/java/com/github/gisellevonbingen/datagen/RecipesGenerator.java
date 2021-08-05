package com.github.gisellevonbingen.datagen;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class RecipesGenerator extends RecipeProvider
{
	public RecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		for (OreType oreType : OreType.values())
		{
			this.build(oreType, consumer);
		}

	}

	public void processItemToItem(OreType oreType, OreState inputState, OreState outputState, int outputCount, BiFunction<ItemStackIngredient, ItemStack, ItemStackToItemStackRecipeBuilder> function, String name, Consumer<IFinishedRecipe> consumer)
	{
		INamedTag<Item> input = ItemTags.bind(inputState.getStateTagName(oreType).toString());
		ItemStack output = outputState.getItemStack(oreType, outputCount);

		if (input == null || output == null || output.isEmpty() == true)
		{
			return;
		}

		function.apply(ItemStackIngredient.from(input), output).build(consumer, new ResourceLocation(MyFirstMod.MODID, (oreType.name() + "-" + inputState.name() + "-" + name + "-" + outputState.name()).toLowerCase()));
	}

	public void build(OreType oreType, Consumer<IFinishedRecipe> consumer)
	{
		this.processItemToItem(oreType, OreState.ORE, OreState.DUST, 2, ItemStackToItemStackRecipeBuilder::enriching, "enriching", consumer);

		this.processItemToItem(oreType, OreState.CLUMP, OreState.DIRTY_DUST, 1, ItemStackToItemStackRecipeBuilder::crushing, "crushing", consumer);
		this.processItemToItem(oreType, OreState.DIRTY_DUST, OreState.DUST, 1, ItemStackToItemStackRecipeBuilder::enriching, "enriching", consumer);

		this.processItemToItem(oreType, OreState.DUST, OreState.INGOT, 1, ItemStackToItemStackRecipeBuilder::smelting, "smelting", consumer);
	}

}
