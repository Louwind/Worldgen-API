package github.Louwind.Features.pool;

import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.function.FeatureFunction;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class StructurePoolEntry {

	private final List<FeatureFunction> functions;
	private final List<FeatureContextSetter> setters;

	private final Identifier id;

	public StructurePoolEntry(Identifier id, FeatureFunction[] functions, FeatureContextSetter[] setters) {
		this.functions = Arrays.asList(functions);
		this.setters = Arrays.asList(setters);
		
		this.id = id;
	}

	public List<FeatureFunction> getFunctions() {
		return this.functions;
	}

	public Identifier getId() {
		return this.id;
	}
	
	public List<FeatureContextSetter> getSetters() {
		return this.setters;
	}
	
}
