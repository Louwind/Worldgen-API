package github.Louwind.Features.impl.context.provider;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
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

    public static final FeatureContextProvider EMPTY = new GenericContextProvider(new BlockRotation[]{ BlockRotation.NONE });

    private final List<FeatureContextOverride> overrides;
    private final List<BlockRotation> rotations;

    public GenericContextProvider(BlockRotation[] rotations, FeatureContextOverride ...overrides) {
        this.overrides = Arrays.asList(overrides);
        this.rotations = Arrays.asList(rotations);
    }

    @Override
    public FeatureContextBuilder getBuilder(FeaturePool pool, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos) {
        StructurePool structurePool = pool.getStructurePool();

        Chunk chunk = world.getChunk(pos);
        ChunkPos chunkPos = chunk.getPos();
        Set<BlockPos> root = Sets.newHashSet(pos);

        return new FeatureContextBuilder()
                .put(BOX, BlockBox.infinite())
                .put(CHUNK_POS, chunkPos)
                .put(POS, pos)
                .put(RANDOM, random)
                .put(ROOT, root)
                .put(ROTATION, rotation)
                .put(STRUCTURE_POOL, structurePool)
                .put(STRUCTURE_WORLD_ACCESS, world);
    }

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return this.overrides;
    }

    @Override
    public BlockRotation getRotations(Random random) {
        int size = this.rotations.size();
        int index = random.nextInt(size);

        return this.rotations.get(index);
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
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context, "rotations");

            return new GenericContextProvider(rotations, overrides);
        }

    }

}
