package github.Louwind.Features.metadata;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class MetadataHandlerType extends JsonSerializableType<MetadataHandler> {

    public MetadataHandlerType(JsonSerializer<? extends MetadataHandler> jsonSerializer) {
        super(jsonSerializer);
    }

}
