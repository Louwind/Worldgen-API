package github.Louwind.Features.metadata.condition;

import com.mojang.serialization.Codec;

public interface MetadataConditionType<T extends MetadataCondition> {

    Codec<T> codec();

}
