package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.registry.FeaturesRegistry;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class SetLootTableFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final Identifier lootBehavior;
    private final Identifier lootTable;

    public SetLootTableFunction(Identifier lootBehavior, Identifier lootTable, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.lootBehavior = lootBehavior;
        this.lootTable = lootTable;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.SET_LOOT_TABLE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @SuppressWarnings("unckecked")
    @Override
    public void accept(FeatureContext context) {
        Structure.StructureBlockInfo blockInfo = context.get(BLOCK_INFO);
        StructureWorldAccess world = context.get(WORLD);
        Random random = context.get(RANDOM);

        BlockEntity blockEntity = world.getBlockEntity(blockInfo.pos);

        if(FeaturesRegistry.LOOT_BEHAVIOR.containsId(this.lootBehavior)) {
            LootBehavior lootBehavior = FeaturesRegistry.LOOT_BEHAVIOR.get(this.lootBehavior);

            ServerWorld server = world.toServerWorld();
            long seed = random.nextLong();

            lootBehavior.setLootTable(this.lootTable, server, blockEntity, blockInfo.pos, seed);
        }

    }

    public static class Serializer implements JsonSerializer<SetLootTableFunction> {

        @Override
        public void toJson(JsonObject json, SetLootTableFunction object, JsonSerializationContext context) {

        }

        @Override
        public SetLootTableFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            Identifier lootBehavior = FeaturesJsonHelper.getIdentifier(json, "loot_behavior");
            Identifier lootTable = FeaturesJsonHelper.getIdentifier(json, "loot_table");

            return new SetLootTableFunction(lootBehavior, lootTable, conditions);
        }

    }

}
