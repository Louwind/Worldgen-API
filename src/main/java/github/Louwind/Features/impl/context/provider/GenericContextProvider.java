package github.Louwind.Features.impl.context.provider;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class GenericContextProvider implements FeatureContextProvider {

    public static final FeatureContextProvider EMPTY = new GenericContextProvider();

    private final List<FeatureContextOverride> overrides;

    public GenericContextProvider(FeatureContextOverride ...overrides) {
        this.overrides = Arrays.asList(overrides);
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(BOX, CHUNK_POS, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, WORLD);
    }

    @Override
    public FeatureContextBuilder getBuilder(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos) {
        OptionalContextParameter<StructurePool> structurePool = pool.getStructurePool();

        Chunk chunk = world.getChunk(pos);
        ChunkPos chunkPos = chunk.getPos();
        Set<BlockPos> root = Sets.newHashSet(pos);

        return new FeatureContextBuilder()
                .put(BOX, BlockBox.infinite())
                .put(CHUNK_POS, chunkPos)
                .put(PIECES, pieces)
                .put(POS, pos)
                .put(RANDOM, random)
                .put(ROOT, root)
                .put(ROTATION, rotation)
                .put(STRUCTURE_POOL, structurePool)
                .put(WORLD, world);
    }

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return this.overrides;
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.PROVIDER;
    }

    public static class Serializer implements JsonSerializer<GenericContextProvider> {

        @Override
        public void toJson(JsonObject json, GenericContextProvider object, JsonSerializationContext context) {

        }

        @Override
        public GenericContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");

            return new GenericContextProvider(overrides);
        }

    }

}
