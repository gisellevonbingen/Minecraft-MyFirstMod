package com.github.gisellevonbingen.datagen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapedRecipeBuilder
{
	private final ResourceLocation id;
	private Item output;
	private int count;
	private String group;
	private final List<String> patterns;
	private final Map<Character, Ingredient> keys;

	public ShapedRecipeBuilder(ResourceLocation id)
	{
		this.id = id;
		this.patterns = new ArrayList<>();
		this.keys = new HashMap<>();

		this.setCount(1);
		this.setGroup("");
	}

	public ShapedRecipeBuilder(ResourceLocation id, Item output, int count)
	{
		this(id);

		this.setOutput(output);
		this.setCount(count);
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public Item getOutput()
	{
		return this.output;
	}

	public ShapedRecipeBuilder setOutput(Item output)
	{
		this.output = output;
		return this;
	}

	public int getCount()
	{
		return this.count;
	}

	public ShapedRecipeBuilder setCount(int count)
	{
		this.count = count;
		return this;
	}

	public String getGroup()
	{
		return this.group;
	}

	public ShapedRecipeBuilder setGroup(String group)
	{
		this.group = group;
		return this;
	}

	public List<String> getPatterns()
	{
		return this.patterns;
	}

	public ShapedRecipeBuilder addPattern(String pattern)
	{
		this.patterns.add(pattern);
		return this;
	}

	public ShapedRecipeBuilder addPattern(Collection<String> patterns)
	{
		this.patterns.addAll(patterns);
		return this;
	}

	public ShapedRecipeBuilder addPattern(String... patterns)
	{
		return this.addPattern(Lists.newArrayList(patterns));
	}

	public Map<Character, Ingredient> getKey()
	{
		return this.keys;
	}

	public ShapedRecipeBuilder addKey(Character key, Ingredient ingredient)
	{
		this.keys.put(key, ingredient);
		return this;
	}

	public IRecipeSerializer<?> getType()
	{
		return (IRecipeSerializer<?>) IRecipeSerializer.SHAPED_RECIPE;
	}

	public Result getResult()
	{
		return new Result(this);
	}

	public static class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<String> patterns;
		private final Map<Character, Ingredient> keys;
		private final IRecipeSerializer<?> type;

		public Result(ShapedRecipeBuilder builder)
		{
			this.id = builder.id;
			this.result = builder.output;
			this.count = builder.count;
			this.group = builder.group;
			this.patterns = new ArrayList<>(builder.patterns);
			this.keys = new HashMap<>(builder.keys);
			this.type = builder.getType();
		}

		public void serializeRecipeData(JsonObject json)
		{
			if (!Strings.isNullOrEmpty(this.group))
			{
				json.addProperty("group", this.group);
			}

			JsonArray patternJson = new JsonArray();

			for (final String lvt_4_1_ : this.patterns)
			{
				patternJson.add(lvt_4_1_);
			}

			json.add("pattern", patternJson);

			JsonObject lvt_3_1_ = new JsonObject();

			for (final Map.Entry<Character, Ingredient> lvt_5_1_ : this.keys.entrySet())
			{
				lvt_3_1_.add(String.valueOf(lvt_5_1_.getKey()), lvt_5_1_.getValue().toJson());
			}

			json.add("key", lvt_3_1_);

			JsonObject resultJson = new JsonObject();

			resultJson.addProperty("item", this.result.getRegistryName().toString());

			if (this.count > 1)
			{
				resultJson.addProperty("count", this.count);
			}

			json.add("result", resultJson);
		}

		public ResourceLocation getId()
		{
			return this.id;
		}

		public Item getResult()
		{
			return this.result;
		}

		public int getCount()
		{
			return this.count;
		}

		public String getGroup()
		{
			return this.group;
		}

		public List<String> getPatterns()
		{
			return Lists.newArrayList(this.patterns);
		}

		public Map<Character, Ingredient> getKeys()
		{
			return Maps.newHashMap(this.keys);
		}

		public IRecipeSerializer<?> getType()
		{
			return this.type;
		}

		@Override
		public ResourceLocation getAdvancementId()
		{
			return null;
		}

		@Override
		public JsonObject serializeAdvancement()
		{
			return null;
		}

	}

}
