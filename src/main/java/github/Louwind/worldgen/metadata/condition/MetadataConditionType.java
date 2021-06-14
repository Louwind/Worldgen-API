package github.Louwind.worldgen.metadata.condition;

import com.mojang.serialization.Codec;

import static github.Louwind.worldgen.registry.Registries.METADATA_CONDITION_TYPE;

public interface MetadataConditionType<T extends MetadataCondition> {

    Codec<MetadataCondition> CODEC = METADATA_CONDITION_TYPE.dispatch("condition", MetadataCondition::getType, MetadataConditionType::codec);

    Codec<T> codec();

}
