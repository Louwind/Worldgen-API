package github.Louwind.Features.processor;

import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureProcessorType extends JsonSerializableType<StructureProcessor> {

	public FeatureProcessorType(JsonSerializer<StructureProcessor> jsonSerializer) {
		super(jsonSerializer);
	}

}
