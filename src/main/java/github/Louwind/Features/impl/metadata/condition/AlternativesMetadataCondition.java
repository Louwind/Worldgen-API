package github.Louwind.Features.impl.metadata.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static github.Louwind.Features.impl.init.MetadataConditions.ALTERNATIVES;
import static github.Louwind.Features.registry.Registries.METADATA_CONDITION_TYPE;

public class AlternativesMetadataCondition implements MetadataCondition {

    protected final MetadataCondition[] terms;

    public AlternativesMetadataCondition(MetadataCondition[] terms) {
        this.terms = terms;
    }

    @Override
    public MetadataConditionType getType() {
        return ALTERNATIVES;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return Stream.of(this.terms).anyMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random));
    }

    public static class Serializer implements JsonSerializer<AlternativesMetadataCondition> {

        @Override
        public void toJson(JsonObject json, AlternativesMetadataCondition object, JsonSerializationContext context) {
            json.addProperty("type", Objects.requireNonNull(METADATA_CONDITION_TYPE.getId(object.getType())).toString());

            if (!ArrayUtils.isEmpty(object.terms))
                json.add("terms", context.serialize(object.terms));
        }

        @Override
        public AlternativesMetadataCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            MetadataCondition[] conditions = FeaturesJsonHelper.getMetadataConditions(json, context, "terms");

            return new AlternativesMetadataCondition(conditions);
        }

    }

}
