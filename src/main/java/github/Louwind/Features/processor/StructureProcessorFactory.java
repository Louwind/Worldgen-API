package github.Louwind.Features.processor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.structure.processor.StructureProcessor;

public interface StructureProcessorFactory<T extends StructureProcessor> {

	T fromJson(JsonObject json, JsonDeserializationContext context);

	Class<T> getProcessorClass();

}
