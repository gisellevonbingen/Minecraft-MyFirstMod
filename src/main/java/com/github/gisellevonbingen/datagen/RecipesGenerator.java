package com.github.gisellevonbingen.datagen;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.github.gisellevonbingen.MyFirstMod;
import com.github.gisellevonbingen.common.MyFirstModSlurries;
import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;
import com.github.gisellevonbingen.function.ThreeFunction;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ChemicalDissolutionRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidSlurryToSlurryRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackGasToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.inputs.FluidStackIngredient;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;
import mekanism.api.recipes.inputs.chemical.SlurryStackIngredient;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

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
			GasStackIngredient hydrogenChloride = GasStackIngredient.from(new GasStack(MekanismGases.HYDROGEN_CHLORIDE.get(), 1));
			GasStackIngredient oxygen = GasStackIngredient.from(new GasStack(MekanismGases.OXYGEN.get(), 1));
			GasStackIngredient sulfuricAcid = GasStackIngredient.from(new GasStack(MekanismGases.SULFURIC_ACID.get(), 1));
			FluidStackIngredient water = FluidStackIngredient.from(new FluidStack(Fluids.WATER, 5));

			SlurryRegistryObject<Slurry, Slurry> slurryRegistry = MyFirstModSlurries.getSlurryRegistry(this.oreType);
			Slurry dirtySlurry = slurryRegistry.getDirtySlurry();
			Slurry cleanSlurry = slurryRegistry.getCleanSlurry();

			this.buildChemicalDissolution(OreState.ORE, dirtySlurry, 1000, sulfuricAcid);
			this.buildChemicalWashing(water, dirtySlurry, cleanSlurry);
			this.buildChemicalCrystallizing(cleanSlurry, OreState.CRYSTAL, 1);

			this.buildItemStackGasToItemStack(OreState.ORE, OreState.SHARD, 4, hydrogenChloride, ItemStackGasToItemStackRecipeBuilder::injecting);
			this.buildItemStackGasToItemStack(OreState.ORE, OreState.CLUMP, 3, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			this.buildItemToItemStack(OreState.ORE, OreState.DUST, 2, ItemStackToItemStackRecipeBuilder::enriching);

			this.buildItemStackGasToItemStack(OreState.CRYSTAL, OreState.SHARD, 1, hydrogenChloride, ItemStackGasToItemStackRecipeBuilder::injecting);
			this.buildItemStackGasToItemStack(OreState.SHARD, OreState.CLUMP, 1, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			this.buildItemToItemStack(OreState.CLUMP, OreState.DIRTY_DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
			this.buildItemToItemStack(OreState.DIRTY_DUST, OreState.DUST, 1, ItemStackToItemStackRecipeBuilder::enriching);
			this.buildSmelting(OreState.DUST, OreState.INGOT);

			this.buildItemToItemStack(OreState.INGOT, OreState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
		}

		public void build(String name, String outputState, BiConsumer<Consumer<IFinishedRecipe>, ResourceLocation> consumer)
		{
			consumer.accept(this.consumer, this.getRecipeName(outputState, name));
		}

		public void buildChemicalCrystallizing(Slurry slurryInput, OreState stateOutput, int outputCount)
		{
			ItemStack output = stateOutput.getItemStack(this.oreType, outputCount);

			if (slurryInput == null || output == null || output.isEmpty() == true)
			{
				return;
			}

			SlurryStackIngredient slurryStackInput = SlurryStackIngredient.from(new SlurryStack(slurryInput, 200));
			this.build("from_slurry", stateOutput.name(), ChemicalCrystallizerRecipeBuilder.crystallizing(slurryStackInput, output)::build);
		}

		public void buildChemicalWashing(FluidStackIngredient fluidInput, Slurry slurryInput, Slurry slurryOutput)
		{
			if (fluidInput == null || slurryInput == null || slurryOutput == null)
			{
				return;
			}

			SlurryStackIngredient slurryStackInput = SlurryStackIngredient.from(new SlurryStack(slurryInput, 1));
			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, 1);
			this.build("clean", "slurry", FluidSlurryToSlurryRecipeBuilder.washing(fluidInput, slurryStackInput, slurryStackOutput)::build);
		}

		public void buildChemicalDissolution(OreState stateInput, Slurry slurryOutput, int outputAmount, GasStackIngredient gasInput)
		{
			ItemStackIngredient itemInput = this.getItemStackIngredient(stateInput);

			if (itemInput == null || slurryOutput == null || gasInput == null)
			{
				return;
			}

			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, outputAmount);
			this.build("dirty", "slurry", ChemicalDissolutionRecipeBuilder.dissolution(itemInput, gasInput, slurryStackOutput)::build);
		}

		public void buildItemStackGasToItemStack(OreState stateInput, OreState stateOutput, int outputCount, GasStackIngredient gasInput, ThreeFunction<ItemStackIngredient, GasStackIngredient, ItemStack, ItemStackGasToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient itemInput = this.getItemStackIngredient(stateInput);
			ItemStack output = stateOutput.getItemStack(this.oreType, outputCount);

			if (itemInput == null || output == null || output.isEmpty() == true || gasInput == null)
			{
				return;
			}

			this.build("from_" + stateInput.name(), stateOutput.name(), function.apply(itemInput, gasInput, output)::build);
		}

		public void buildItemToItemStack(OreState stateInput, OreState stateOutput, int outputCount, BiFunction<ItemStackIngredient, ItemStack, ItemStackToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient itemInput = this.getItemStackIngredient(stateInput);
			ItemStack output = stateOutput.getItemStack(this.oreType, outputCount);

			if (itemInput == null || output == null || output.isEmpty() == true)
			{
				return;
			}

			this.build("from_" + stateInput.name(), stateOutput.name(), function.apply(itemInput, output)::build);
		}

		private void buildSmelting(OreState stateInput, OreState stateOutput)
		{
			Ingredient itemInput = Ingredient.of(stateInput.getItemStack(this.oreType));
			Item output = stateOutput.getItem(this.oreType);

			if (itemInput == null || output == null)
			{
				return;
			}

			ResourceLocation smelting = this.getRecipeName(stateOutput.name(), "from_" + stateInput.name() + "_smelting");
			this.consumer.accept(new CookingRecipeBuilder.Result(smelting, "", itemInput, output, 0.3F, 200, Builder.advancement(), smelting, IRecipeSerializer.SMELTING_RECIPE));
		}

		public ResourceLocation getRecipeName(String stateOutput, String name)
		{
			return new ResourceLocation(MyFirstMod.MODID, ("processing/" + this.oreType.name() + "/" + stateOutput + "/" + name).toLowerCase());
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
