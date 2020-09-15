package github.Louwind.Features.generator;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public interface StructureGeneratorFactory<T extends StructureGenerator> extends JsonDeserializer<T> {
	
	@Deprecated
	Class<T> getFactoryClass();
	
	@Deprecated
	default GsonBuilder getGson() {
		return new GsonBuilder();
	}

}
