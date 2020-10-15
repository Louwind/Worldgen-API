package github.Louwind.Features.metadata;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureMetadataType extends JsonSerializableType<FeatureMetadata> {

    public FeatureMetadataType(JsonSerializer<? extends FeatureMetadata> jsonSerializer) {
        super(jsonSerializer);
    }

}
