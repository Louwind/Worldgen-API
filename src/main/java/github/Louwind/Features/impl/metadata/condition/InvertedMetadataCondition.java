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

import static github.Louwind.Features.impl.init.MetadataConditions.INVERTED;
import static github.Louwind.Features.registry.Registries.METADATA_CONDITION_TYPE;

public class InvertedMetadataCondition implements MetadataCondition {

    private final MetadataCondition[] terms;

    public InvertedMetadataCondition(MetadataCondition[] terms) {
        this.terms = terms;
    }

    @Override
    public MetadataConditionType getType() {
        return INVERTED;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return !Stream.of(this.terms).map(condition -> condition.test(world, blockInfo, blockPos, rotation, random)).reduce(false, Boolean::logicalAnd);
    }

    public static class Serializer implements JsonSerializer<InvertedMetadataCondition> {

        @Override
        public void toJson(JsonObject json, InvertedMetadataCondition object, JsonSerializationContext context) {
            json.addProperty("type", Objects.requireNonNull(METADATA_CONDITION_TYPE.getId(object.getType())).toString());

            if (!ArrayUtils.isEmpty(object.terms))
                json.add("terms", context.serialize(object.terms));
        }

        @Override
        public InvertedMetadataCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            MetadataCondition[] conditions = FeaturesJsonHelper.getMetadataConditions(json, context,  "terms");

            return new InvertedMetadataCondition(conditions);
        }

    }

}
