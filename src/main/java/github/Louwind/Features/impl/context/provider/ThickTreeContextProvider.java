package github.Louwind.Features.impl.context.provider;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.block.sapling.FeaturesThickSaplingGenerator;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawPieceGenerator;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class ThickTreeContextProvider extends TreeContextProvider {

    private final Block sapling;

    public ThickTreeContextProvider(Block sapling, BlockRotation[] rotations, FeatureContextOverride[] overrides) {
        super(rotations, overrides);

        this.sapling = sapling;
    }

    @Override
    public FeatureContextBuilder getFeatureContextBuilder(StructureWorldAccess world, PoolFeatureConfig config, BlockRotation rotation, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos origin) {
        FeatureContextBuilder builder = super.getFeatureContextBuilder(world, config, rotation, chunkGenerator, pieces, random, origin);

        ServerWorld server = world.toServerWorld();

        DynamicRegistryManager registryManager = server.getRegistryManager();
        StructureManager structureManager = server.getStructureManager();

        Set<BlockPos> saplings = FeaturesThickSaplingGenerator.getSaplings(world, this.sapling, origin);
        BlockPos pos = saplings.stream().sorted().iterator().next();

        return builder.put(ORIGIN, origin)
                .put(PIECES, JigsawPieceGenerator.addPieces(registryManager, structureManager, config, chunkGenerator, random, pos))
                .put(POS, pos)
                .put(ROOT, saplings);
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.THICK_TREE;
    }

    public static class Serializer implements JsonSerializer<ThickTreeContextProvider> {

        @Override
        public void toJson(JsonObject json, ThickTreeContextProvider object, JsonSerializationContext context) {

        }

        @Override
        public ThickTreeContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, "rotations");
            Block sapling = FeaturesJsonHelper.getBlock(json, "sapling");

            return new ThickTreeContextProvider(sapling, rotations, overrides);
        }

    }

}
