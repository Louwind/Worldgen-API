package github.Louwind.Features.feature;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class PoolStructureFeatureType extends JsonSerializableType<PoolStructureFeature> {

    public PoolStructureFeatureType(JsonSerializer<? extends PoolStructureFeature> jsonSerializer) {
        super(jsonSerializer);
    }

}
