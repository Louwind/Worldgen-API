package github.Louwind.Features.impl.entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.pool.DefaultFeaturePool;
import github.Louwind.Features.pool.FeaturePoolProperties;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class DefaultFeaturePoolEntry implements FeatureEntry {

	private final List<FeatureFunction> functions;
	private final List<FeatureContextSetter> setters;
	private final Identifier structureId;

	public DefaultFeaturePoolEntry(Identifier structureId, FeatureFunction[] functions, FeatureContextSetter[] setters) {
		this.functions = Arrays.asList(functions);
		this.setters = Arrays.asList(setters);
		this.structureId = structureId;
	}

	@Override
	public List<FeatureFunction> getFunctions() {
		return this.functions;
	}

	@Override
	public List<FeatureContextSetter> getSetters() {
		return this.setters;
	}

	@Override
	public Identifier getStructureId() {
		return this.structureId;
	}

	public static class Serializer implements JsonSerializer<DefaultFeaturePoolEntry> {

		@Override
		public void toJson(JsonObject json, DefaultFeaturePoolEntry object, JsonSerializationContext context) {
			// TODO toJson
		}

		@Override
		public DefaultFeaturePoolEntry fromJson(JsonObject json, JsonDeserializationContext context) {
			Identifier structureId = FeaturesJsonHelper.getIdentifier(json, "structure");

			FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context, "functions");
			FeatureContextSetter[] setters = FeaturesJsonHelper.getSetters(json, context, "setters");

			return new DefaultFeaturePoolEntry(structureId, functions, setters);
		}

	}

}
