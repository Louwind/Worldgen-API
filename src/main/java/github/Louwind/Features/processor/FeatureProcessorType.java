package github.Louwind.Features.processor;

import com.mojang.serialization.Codec;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureProcessorType<T extends StructureProcessor> extends JsonSerializableType<T> implements StructureProcessorType<T> {

    private final Codec<T> codec;

    public FeatureProcessorType(JsonSerializer<? extends T> jsonSerializer, Codec<T> codec) {
        super(jsonSerializer);

        this.codec = codec;
    }

    @Override
    public Codec<T> codec() {
        return this.codec;
    }

}
