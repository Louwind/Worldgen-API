package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
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
    private final Identifier lootTable;

    public SetLootTableFunction(Identifier lootTable, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
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

    @Override
    public void accept(FeatureContext context) {
        Structure.StructureBlockInfo blockInfo = context.get(BLOCK_INFO);
        StructureWorldAccess world = context.get(WORLD);
        Random random = context.get(RANDOM);

        BlockEntity blockEntity = world.getBlockEntity(blockInfo.pos);

        if(blockEntity != null) {
            long seed = random.nextLong();

            if(blockEntity instanceof LootableContainerBlockEntity) {
                LootableContainerBlockEntity lootable = (LootableContainerBlockEntity) blockEntity;

                lootable.setLootTable(this.lootTable, seed);
            }

        }

    }

    public static class Serializer implements JsonSerializer<SetLootTableFunction> {

        @Override
        public void toJson(JsonObject json, SetLootTableFunction object, JsonSerializationContext context) {

        }

        @Override
        public SetLootTableFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            Identifier lootTable = FeaturesJsonHelper.getIdentifier(json, "loot_table");

            return new SetLootTableFunction(lootTable, conditions);
        }

    }

}
