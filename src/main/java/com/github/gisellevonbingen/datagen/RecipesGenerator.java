package com.github.gisellevonbingen.datagen;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.function.ThreeFunction;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.datagen.recipe.builder.ItemStackGasToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;
import mekanism.common.registries.MekanismGases;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
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
			new OreRecipesGenerator(oreType, consumer).build();
		}

	}

	public class OreRecipesGenerator
	{
		private OreType oreType;
		private Consumer<IFinishedRecipe> consumer;

		public OreRecipesGenerator(OreType oreType, Consumer<IFinishedRecipe> consumer)
		{
			this.oreType = oreType;
			this.consumer = consumer;
		}

		public void build()
		{
			GasStackIngredient hCl = GasStackIngredient.from(new GasStack(MekanismGases.HYDROGEN_CHLORIDE.get(), 1));
			GasStackIngredient oxygen = GasStackIngredient.from(new GasStack(MekanismGases.OXYGEN.get(), 1));

			this.buildItemStackGasToItemStack(OreState.ORE, OreState.SHARD, 4, hCl, ItemStackGasToItemStackRecipeBuilder::injecting);
			this.buildItemStackGasToItemStack(OreState.ORE, OreState.CLUMP, 3, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			this.buildItemToItemStack(OreState.ORE, OreState.DUST, 2, ItemStackToItemStackRecipeBuilder::enriching);

			this.buildItemStackGasToItemStack(OreState.SHARD, OreState.CLUMP, 1, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			this.buildItemToItemStack(OreState.CLUMP, OreState.DIRTY_DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
			this.buildItemToItemStack(OreState.DIRTY_DUST, OreState.DUST, 1, ItemStackToItemStackRecipeBuilder::enriching);
			this.buildItemToItemStack(OreState.DUST, OreState.INGOT, 1, ItemStackToItemStackRecipeBuilder::smelting);

			this.buildItemToItemStack(OreState.INGOT, OreState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
		}

		public void build(OreState inputState, OreState outputState, BiConsumer<Consumer<IFinishedRecipe>, ResourceLocation> consumer)
		{
			consumer.accept(this.consumer, this.getRecipeName(inputState, outputState));
		}

		public void buildItemStackGasToItemStack(OreState inputState, OreState outputState, int outputCount, GasStackIngredient gasInput, ThreeFunction<ItemStackIngredient, GasStackIngredient, ItemStack, ItemStackGasToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient input = this.getItemStackIngredient(inputState);
			ItemStack output = outputState.getItemStack(this.oreType, outputCount);

			if (input == null || output == null || output.isEmpty() == true || gasInput == null)
			{
				return;
			}

			this.build(inputState, outputState, function.apply(input, gasInput, output)::build);
		}

		public void buildItemToItemStack(OreState inputState, OreState outputState, int outputCount, BiFunction<ItemStackIngredient, ItemStack, ItemStackToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient input = this.getItemStackIngredient(inputState);
			ItemStack output = outputState.getItemStack(this.oreType, outputCount);

			if (input == null || output == null || output.isEmpty() == true)
			{
				return;
			}

			this.build(inputState, outputState, function.apply(input, output)::build);
		}

		public ResourceLocation getRecipeName(OreState inputState, OreState outputState)
		{
			return new ResourceLocation(MyFirstMod.MODID, ("processing/" + this.oreType.name() + "/" + outputState.name() + "/from_" + inputState.name()).toLowerCase());
		}

		public ItemStackIngredient getItemStackIngredient(OreState oreState)
		{
			return this.getItemStackIngredient(oreState, 1);
		}

		public ItemStackIngredient getItemStackIngredient(OreState oreState, int amount)
		{
			return ItemStackIngredient.from(ItemTags.bind(oreState.getStateTagName(this.oreType).toString()), amount);
		}

		public OreType getOreType()
		{
			return this.oreType;
		}

		public Consumer<IFinishedRecipe> getConsumer()
		{
			return this.consumer;
		}

	}

}
