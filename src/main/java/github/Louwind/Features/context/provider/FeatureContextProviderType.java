package github.Louwind.Features.context.provider;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextProviderType extends JsonSerializableType<FeatureContextProvider> {

    public FeatureContextProviderType(JsonSerializer<? extends FeatureContextProvider> jsonSerializer) {
        super(jsonSerializer);
    }

}
