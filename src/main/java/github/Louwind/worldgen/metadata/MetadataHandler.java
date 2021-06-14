package github.Louwind.worldgen.metadata;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public interface MetadataHandler {

    Codec<MetadataHandler> CODEC = Registries.METADATA_HANDLER_TYPE.dispatch("type", MetadataHandler::getType, MetadataHandlerType::codec);

    void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

    MetadataHandlerType<?> getType();

}