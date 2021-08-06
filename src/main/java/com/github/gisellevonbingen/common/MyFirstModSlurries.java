package com.github.gisellevonbingen.common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.common.ore.OreState;
import com.github.gisellevonbingen.common.ore.OreType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismSlurries;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MyFirstModSlurries
{
	public static final Map<OreType, SlurryRegistryObject<Slurry, Slurry>> SLURRIES = new HashMap<>();

	public static SlurryRegistryObject<Slurry, Slurry> getSlurryRegistry(OreType oreType)
	{
		return SLURRIES.get(oreType);
	}

	public static void register()
	{
		register(MekanismSlurries.SLURRIES);
	}

	public static void register(SlurryDeferredRegister register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

		for (OreType oreType : OreType.values())
		{
			SlurryRegistryObject<Slurry, Slurry> registryObject = register.register(oreType.getOreName(), new SlurryBuildOperator(oreType));
			SLURRIES.put(oreType, registryObject);
		}

	}

	public static final class SlurryBuildOperator implements UnaryOperator<SlurryBuilder>
	{
		private final OreType oreType;

		private SlurryBuildOperator(OreType oreType)
		{
			this.oreType = oreType;
		}

		@Override
		public SlurryBuilder apply(SlurryBuilder builder)
		{
			INamedTag<Item> tag = OreState.ORE.getStateTag(this.oreType);
			return builder.color(this.oreType.getColor()).ore(tag);
		}

		public OreType getOreType()
		{
			return this.oreType;
		}

	}

}
