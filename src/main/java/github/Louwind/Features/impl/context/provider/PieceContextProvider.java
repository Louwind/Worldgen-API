package github.Louwind.Features.impl.context.provider;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawPieceGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class PieceContextProvider implements FeatureContextProvider {

    public static final FeatureContextProvider EMPTY = new PieceContextProvider(new BlockRotation[]{ BlockRotation.NONE });

    private final List<FeatureContextOverride> overrides;
    private final List<BlockRotation> rotations;

    public PieceContextProvider(BlockRotation[] rotations, FeatureContextOverride ...overrides) {
        this.overrides = Arrays.asList(overrides);
        this.rotations = Arrays.asList(rotations);
    }

    public FeatureContextBuilder getFeatureContextBuilder(StructureWorldAccess world, PoolFeatureConfig config, BlockRotation rotation, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos pos) {
        ServerWorld server = world.toServerWorld();

        DynamicRegistryManager registryManager = server.getRegistryManager();
        StructureManager structureManager = server.getStructureManager();

        return this.getStructureContextBuilder(registryManager, structureManager, config, rotation, chunkGenerator, pieces, random, pos);
    }

    public FeatureContextBuilder getStructureContextBuilder(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, BlockRotation rotation, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos pos) {
        return new FeatureContextBuilder()
                .put(BOX, BlockBox.infinite())
                .put(CHUNK_GENERATOR, chunkGenerator)
                .put(PIECES, JigsawPieceGenerator.addPieces(registryManager, structureManager, config, chunkGenerator, pieces, random, pos))
                .put(POS, pos)
                .put(RANDOM, random)
                .put(ROTATION, rotation);
    }

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return this.overrides;
    }

    public BlockRotation getRotations(Random random) {
        int size = this.rotations.size();
        int index = random.nextInt(size);

        return this.rotations.get(index);
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.PIECE;
    }

    public static class Serializer implements JsonSerializer<PieceContextProvider> {

        @Override
        public void toJson(JsonObject json, PieceContextProvider object, JsonSerializationContext context) {

        }

        @Override
        public PieceContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, "rotations");

            return new PieceContextProvider(rotations, overrides);
        }

    }

}
