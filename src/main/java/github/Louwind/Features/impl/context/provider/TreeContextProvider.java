package github.Louwind.Features.impl.context.provider;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class TreeContextProvider extends GenericContextProvider {

    public TreeContextProvider(BlockRotation[] rotations, FeatureContextOverride[] overrides) {
        super(rotations, overrides);
    }

    @Override
    public Set<FeatureContextParameter<?>> getAllowedParameters() {
        return ImmutableSet.of(HEIGHT);
    }

    @Override
    public FeatureContextBuilder getBuilder(FeaturePool pool, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos) {
        FeatureContextBuilder builder = super.getBuilder(pool, rotation, world, random, pos);

        Set<BlockPos> root = Sets.newHashSet(pos);

        return builder.put(ROOT, root);
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(BOX, CHUNK_POS, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, STRUCTURE_WORLD_ACCESS);
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.TREE;
    }

    public static class Serializer implements JsonSerializer<TreeContextProvider> {

        @Override
        public void toJson(JsonObject json, TreeContextProvider object, JsonSerializationContext context) {

        }

        @Override
        public TreeContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context, "rotations");

            return new TreeContextProvider(rotations, overrides);
        }

    }

}
