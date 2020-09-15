package github.Louwind.Features.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

public interface StructureContextGetterFactory <T extends StructureContextGetter<?>> {

	T fromJson(JsonObject json, JsonDeserializationContext context);
	
}
