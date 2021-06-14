package github.Louwind.Features.metadata.condition;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.function.Function;

import static github.Louwind.Features.registry.Registries.METADATA_CONDITION_TYPE;

public interface MetadataConditionType<T extends MetadataCondition> {

    Codec<MetadataCondition> CODEC = METADATA_CONDITION_TYPE.dispatch("condition", MetadataCondition::getType, MetadataConditionType::codec);

    Codec<T> codec();

}
