package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class PlaceFeatureFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final OptionalBlockPos pos;
    private final Identifier id;

    public PlaceFeatureFunction(Identifier id, OptionalBlockPos pos, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.pos = pos;
        this.id = id;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.PLACE_FEATURE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(POS, RANDOM, WORLD);
    }

    @Override
    public void accept(FeatureContext context) {
        BlockPos pos = context.get(POS);
        Random random = context.get(RANDOM);
        StructureWorldAccess access = context.get(WORLD);

        if(BuiltinRegistries.CONFIGURED_FEATURE.containsId(this.id)) {
            ConfiguredFeature<?, ?> feature = BuiltinRegistries.CONFIGURED_FEATURE.get(this.id);

            ServerWorld server = access.toServerWorld();
            ServerChunkManager manager = server.getChunkManager();
            ChunkGenerator chunkGenerator = manager.getChunkGenerator();

            feature.generate(access, chunkGenerator, random, pos);
        }

    }

    public static class Serializer implements JsonSerializer<PlaceFeatureFunction> {

        @Override
        public void toJson(JsonObject json, PlaceFeatureFunction object, JsonSerializationContext context) {

        }

        @Override
        public PlaceFeatureFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");
            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "pos");
            Identifier id = FeaturesJsonHelper.getIdentifier(json, "feature");

            return new PlaceFeatureFunction(id, pos, conditions);
        }

    }

}
