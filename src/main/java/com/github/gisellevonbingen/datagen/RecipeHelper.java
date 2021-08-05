package com.github.gisellevonbingen.datagen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class RecipeHelper
{
	public static final int DefaultCookTime = 200;

	public static ResourceLocation appendName(ResourceLocation parent, String name)
	{
		return new ResourceLocation(parent.getNamespace(), parent.getPath() + name);
	}

	public static CookingRecipeBuilder.Result smeltingOnly(ResourceLocation recipeName, Ingredient itemInput, Item itemOutput, float exp, int smeltingTime)
	{
		return new CookingRecipeBuilder.Result(recipeName, "", itemInput, itemOutput, exp, smeltingTime, Builder.advancement(), recipeName, IRecipeSerializer.SMELTING_RECIPE);
	}

	public static CookingRecipeBuilder.Result blastingOnly(ResourceLocation recipeName, Ingredient itemInput, Item itemOutput, float exp, int blastingTime)
	{
		return new CookingRecipeBuilder.Result(recipeName, "", itemInput, itemOutput, exp, blastingTime, Builder.advancement(), recipeName, IRecipeSerializer.BLASTING_RECIPE);
	}

	public static List<CookingRecipeBuilder.Result> cook(ResourceLocation recipeNamePrefix, Ingredient itemInput, Item itemOutput, float exp)
	{
		return cook(recipeNamePrefix, itemInput, itemOutput, exp, DefaultCookTime);
	}

	public static ShapelessRecipeBuilder.Result shapeless(ResourceLocation recipeName, Item itemOutput, int outputCount, List<Ingredient> ingredients)
	{
		return new ShapelessRecipeBuilder.Result(recipeName, itemOutput, outputCount, "", ingredients, Builder.advancement(), recipeName);
	}

	public static List<CookingRecipeBuilder.Result> cook(ResourceLocation recipeNamePrefix, Ingredient itemInput, Item itemOutput, float exp, int smeltingTime)
	{
		List<CookingRecipeBuilder.Result> list = new ArrayList<>();
		list.add(smeltingOnly(appendName(recipeNamePrefix, "_smelting"), itemInput, itemOutput, exp, smeltingTime));
		list.add(blastingOnly(appendName(recipeNamePrefix, "_blasting"), itemInput, itemOutput, exp, smeltingTime / 2));

		return list;
	}

	public static void acceptAll(Consumer<IFinishedRecipe> consumer, IFinishedRecipe recipe)
	{
		consumer.accept(recipe);
	}

	public static void acceptAll(Consumer<IFinishedRecipe> consumer, Collection<? extends IFinishedRecipe> recipes)
	{
		for (IFinishedRecipe recipe : recipes)
		{
			consumer.accept(recipe);
		}

	}

	private RecipeHelper()
	{

	}

}
