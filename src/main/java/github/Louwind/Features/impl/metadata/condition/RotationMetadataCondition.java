package github.Louwind.Features.impl.metadata.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.util.codec.CodecHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataConditions.ROTATION;

public class RotationMetadataCondition implements MetadataCondition {

    public static final Codec<RotationMetadataCondition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(CodecHelper.ROTATION.fieldOf("rotation").forGetter(condition -> condition.rotation)).apply(instance, (RotationMetadataCondition::new)));

    private final BlockRotation rotation;

    public RotationMetadataCondition(BlockRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public MetadataConditionType<?> getType() {
        return ROTATION;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return this.rotation == rotation;
    }

}
