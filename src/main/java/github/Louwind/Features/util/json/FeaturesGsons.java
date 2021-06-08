package github.Louwind.Features.util.json;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.util.MetadataHandlerList;
import github.Louwind.Features.util.json.adapter.BlockStateAdapter;
import github.Louwind.Features.util.json.adapter.MetadataHandlerListAdapter;
import github.Louwind.Features.util.json.adapter.Vec3dAdapter;
import net.minecraft.block.BlockState;
import net.minecraft.util.JsonSerializing;
import net.minecraft.util.math.Vec3d;

import static github.Louwind.Features.registry.Registries.*;

public class FeaturesGsons {

    private static Object createLootBehaviorConditionSerializer() {
        return JsonSerializing.createTypeHandler(LOOT_BEHAVIOR_CONDITION, "condition", "condition", LootBehaviorCondition::getType).createGsonSerializer();
    }

    private static Object createLootBehaviorSerializer() {
        return JsonSerializing.createTypeHandler(LOOT_BEHAVIOR, "type", "type", LootBehavior::getType).createGsonSerializer();
    }

    private static Object createMetadataConditionSerializer() {
        return JsonSerializing.createTypeHandler(METADATA_CONDITION_TYPE, "condition", "condition", MetadataCondition::getType).createGsonSerializer();
    }

    private static Object createMetadataHandlerSerializer() {
        return JsonSerializing.createTypeHandler(METADATA_HANDLER_TYPE, "type", "type", MetadataHandler::getType).createGsonSerializer();
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(BlockState.class, new BlockStateAdapter())
                .registerTypeAdapter(MetadataHandlerList.class, new MetadataHandlerListAdapter())
                .registerTypeAdapter(Vec3d.class, new Vec3dAdapter())
                .registerTypeHierarchyAdapter(LootBehavior.class, FeaturesGsons.createLootBehaviorSerializer())
                .registerTypeHierarchyAdapter(LootBehaviorCondition.class, FeaturesGsons.createLootBehaviorConditionSerializer())
                .registerTypeHierarchyAdapter(MetadataHandler.class, FeaturesGsons.createMetadataHandlerSerializer())
                .registerTypeHierarchyAdapter(MetadataCondition.class, FeaturesGsons.createMetadataConditionSerializer());
    }

}
