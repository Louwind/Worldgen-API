package github.Louwind.Features.pool.element;

import com.mojang.serialization.Codec;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeaturePoolElementType<T extends StructurePoolElement> extends JsonSerializableType<FeaturesPoolElementFunction<T>> implements StructurePoolElementType<T> {

    private final Codec<T> codec;

    public FeaturePoolElementType(JsonSerializer<FeaturesPoolElementFunction<T>> jsonSerializer, Codec<T> codec) {
        super(jsonSerializer);

        this.codec = codec;
    }

    @Override
    public Codec<T> codec() {
        return this.codec;
    }

}
