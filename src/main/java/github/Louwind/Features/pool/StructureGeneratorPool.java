package github.Louwind.Features.pool;

import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.function.FeatureFunction;
import net.minecraft.structure.pool.StructurePool;

import java.util.Arrays;
import java.util.List;

public class StructureGeneratorPool {

	private final List<StructurePoolEntry> entries;
	private final List<FeatureFunction> functions;
	private final List<FeatureContextSetter> setters;
	private final OptionalContextParameter<StructurePool> pool;

	public StructureGeneratorPool(OptionalContextParameter<StructurePool> pool, FeatureContextSetter[] setters, FeatureFunction[] functions, StructurePoolEntry... entries) {
		this.entries = Arrays.asList(entries);
		this.functions = Arrays.asList(functions);
		this.setters = Arrays.asList(setters);

		this.pool = pool;
	}

	public OptionalContextParameter<StructurePool> getStructurePool() {
		return this.pool;
	}

	public List<StructurePoolEntry> getEntries() {
		return this.entries;
	}

	public List<FeatureFunction> getFunctions() {
		return this.functions;
	}

	public List<FeatureContextSetter> getSetters() {
		return this.setters;
	}
	
}
