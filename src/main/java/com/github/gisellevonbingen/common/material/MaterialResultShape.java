package com.github.gisellevonbingen.common.material;

import java.util.List;

import com.google.common.collect.Lists;

public enum MaterialResultShape
{
	INGOT(MaterialState.ORE, MaterialState.CRYSTAL, MaterialState.SHARD, MaterialState.CLUMP, MaterialState.DIRTY_DUST, MaterialState.DUST, MaterialState.INGOT, MaterialState.NUGGET),
	GEM(MaterialState.ORE, MaterialState.CRYSTAL, MaterialState.SHARD, MaterialState.CLUMP, MaterialState.DIRTY_DUST, MaterialState.DUST, MaterialState.GEM),;

	private List<MaterialState> processableStates;

	private MaterialResultShape(MaterialState... processableStates)
	{
		this(Lists.newArrayList(processableStates));
	}

	private MaterialResultShape(List<MaterialState> processableStates)
	{
		this.processableStates = processableStates;
	}

	public boolean canProcess(MaterialState state)
	{
		return this.processableStates.contains(state);
	}

	public boolean canProcess(MaterialState... states)
	{
		for (MaterialState state : states)
		{
			if (this.processableStates.contains(state) == false)
			{
				return false;
			}

		}

		return true;
	}

	public boolean canProcess(Iterable<MaterialState> states)
	{
		for (MaterialState state : states)
		{
			if (this.processableStates.contains(state) == false)
			{
				return false;
			}

		}

		return true;
	}

	public List<MaterialState> getProcessableStates()
	{
		return Lists.newArrayList(this.processableStates);
	}

}
