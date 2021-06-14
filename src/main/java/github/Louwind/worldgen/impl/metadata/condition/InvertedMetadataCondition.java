package github.Louwind.worldgen.impl.metadata.condition;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.worldgen.metadata.condition.MetadataCondition;
import github.Louwind.worldgen.metadata.condition.MetadataConditionType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static github.Louwind.worldgen.impl.init.MetadataConditions.INVERTED;

public class InvertedMetadataCondition implements MetadataCondition {

    public static final Codec<InvertedMetadataCondition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(MetadataConditionType.CODEC.listOf().fieldOf("terms").orElseGet(Lists::newArrayList).forGetter(handler -> handler.terms)).apply(instance, (InvertedMetadataCondition::new)));

    private final List<MetadataCondition> terms;

    public InvertedMetadataCondition(List<MetadataCondition> terms) {
        this.terms = terms;
    }

    @Override
    public MetadataConditionType<?> getType() {
        return INVERTED;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return !this.terms.stream().map(condition -> condition.test(world, blockInfo, blockPos, rotation, random)).reduce(false, Boolean::logicalAnd);
    }

}
