package github.Louwind.Features.metadata;

import com.mojang.serialization.Codec;
import github.Louwind.Features.registry.Registries;
import github.Louwind.Features.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.dynamic.RegistryElementCodec;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.function.Supplier;

public interface MetadataHandler {

    Codec<MetadataHandler> CODEC = Registries.METADATA_HANDLER_TYPE.dispatch("type", MetadataHandler::getType, MetadataHandlerType::codec);

    void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

    MetadataHandlerType<?> getType();

}