package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static github.Louwind.Features.registry.Registries.METADATA_HANDLER_TYPE;

public abstract class ConditionalMetadataHandler implements MetadataHandler {

    protected final MetadataCondition[] conditions;

    public ConditionalMetadataHandler(MetadataCondition[] conditions) {
        this.conditions = conditions;
    }

    @Override
    public void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {

        if(Arrays.stream(this.conditions).allMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random)))
            this.process(world, blockInfo, blockPos, rotation, random);

    }

    abstract void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

    public abstract static class Serializer<T extends ConditionalMetadataHandler> implements JsonSerializer<T> {

        @Override
        public void toJson(JsonObject json, T object, JsonSerializationContext context) {
            json.addProperty("type", Objects.requireNonNull(METADATA_HANDLER_TYPE.getId(object.getType())).toString());

            if (!ArrayUtils.isEmpty(object.conditions))
                json.add("conditions", context.serialize(object.conditions));
        }

    }

}