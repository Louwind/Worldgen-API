package github.Louwind.Features.generator;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureGeneratorType extends JsonSerializableType<FeatureGenerator> {

    public FeatureGeneratorType(JsonSerializer<? extends FeatureGenerator> jsonSerializer) {
        super(jsonSerializer);
    }

}
