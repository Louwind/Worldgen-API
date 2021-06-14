package github.Louwind.worldgen.metadata.condition;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public interface MetadataCondition {

    boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

    MetadataConditionType<?> getType();

}
