package github.Louwind.worldgen.util;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.metadata.MetadataHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class MetadataHandlerList {

    public static final Codec<MetadataHandlerList> CODEC = MetadataHandler.CODEC.listOf().xmap(MetadataHandlerList::new, MetadataHandlerList::getList);

    private final List<MetadataHandler> metadata;

    public MetadataHandlerList(List<MetadataHandler> metadata) {
        this.metadata = metadata;
    }

    public void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos pos, BlockRotation rotation, Random random) {
        this.metadata.forEach(metadata -> metadata.handle(world, blockInfo, pos, rotation, random));
    }

    public List<MetadataHandler> getList() {
        return this.metadata;
    }

}
