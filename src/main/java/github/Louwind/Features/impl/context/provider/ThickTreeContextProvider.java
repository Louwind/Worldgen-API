package github.Louwind.Features.impl.context.provider;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.block.sapling.FeaturesThickSaplingGenerator;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawPieceGenerator;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
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
    public FeatureContextBuilder getBuilder(JigsawFeatureConfig config, BlockRotation rotation, StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos origin) {
        FeatureContextBuilder builder = super.getBuilder(config, rotation, world, chunkGenerator, random, origin);

        Set<BlockPos> saplings = FeaturesThickSaplingGenerator.getSaplings(world, this.sapling, origin);
        BlockPos pos = saplings.stream().sorted().iterator().next();

        List<PoolStructurePiece> pieces = JigsawPieceGenerator.getPieces(world, config, chunkGenerator, random, pos);

        return builder.put(ORIGIN, origin)
                .put(PIECES, pieces)
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
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context, "rotations");
            Block sapling = FeaturesJsonHelper.getBlock(json, "sapling");

            return new ThickTreeContextProvider(sapling, rotations, overrides);
        }

    }

}
