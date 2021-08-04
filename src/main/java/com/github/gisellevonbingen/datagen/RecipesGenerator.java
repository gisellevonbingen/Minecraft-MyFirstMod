package com.github.gisellevonbingen.datagen;

import java.util.function.Consumer;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.Registration;

import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class RecipesGenerator extends RecipeProvider
{
	public RecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> p_200404_1_)
	{
		String modid = MyFirstMod.MODID;
		ItemStackToItemStackRecipeBuilder.smelting(ItemStackIngredient.from(ItemTags.COALS), new ItemStack(Registration.ROCK.get(), 64)).build(p_200404_1_);
		ItemStackToItemStackRecipeBuilder.enriching(ItemStackIngredient.from(new ItemStack(Registration.ROCK.get())), new ItemStack(Items.STONE)).build(p_200404_1_, new ResourceLocation(modid, "rock_enriching"));
		ItemStackToItemStackRecipeBuilder.crushing(ItemStackIngredient.from(new ItemStack(Registration.ROCK.get())), new ItemStack(Items.COBBLESTONE)).build(p_200404_1_, new ResourceLocation(modid, "rock_crushing"));
	}

}
