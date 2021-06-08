package github.Louwind.Features.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.util.LootBehaviorConditionList;
import github.Louwind.Features.util.LootBehaviorList;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;

public class FeaturesJsonHelper {

    public static Identifier asIdentifier(JsonElement element, String name) {
        var id = JsonHelper.asString(element, name);

        return new Identifier(id);
    }

    public static BlockState getBlockState(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, context, BlockState.class);
    }

    public static Identifier getIdentifier(JsonObject object, String name) {
        return FeaturesJsonHelper.asIdentifier(object.get(name), name);
    }

    public static MetadataHandler[] getMetadataHandlers(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, context, MetadataHandler[].class);
    }

    public static MetadataCondition[] getMetadataConditions(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, new MetadataCondition[0], context, MetadataCondition[].class);
    }

    @SuppressWarnings("unchecked")
    public static LootBehaviorList getLootBehaviors(JsonObject object, JsonDeserializationContext context, String name) {
        var conditions = (LootBehavior<BlockEntity>[]) JsonHelper.deserialize(object, name, new LootBehavior[0], context, LootBehavior[].class);

        return new LootBehaviorList(Arrays.asList(conditions));
    }

    @SuppressWarnings("unchecked")
    public static LootBehaviorConditionList getLootBehaviorConditions(JsonObject object, JsonDeserializationContext context, String name) {

        if(object.has(name)) {
            var conditions = (LootBehaviorCondition<BlockEntity>[]) JsonHelper.deserialize(object, name, new LootBehaviorCondition[0], context, LootBehaviorCondition[].class);

            return new LootBehaviorConditionList(Arrays.asList(conditions));
        }

        return LootBehaviorConditionList.EMPTY;
    }

    public static BlockRotation getRotation(JsonObject object, String name) {
        var string = JsonHelper.getString(object, name);

        return BlockRotation.valueOf(string.toUpperCase());
    }

    public static Vec3d getVector(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, context, Vec3d.class);
    }

}