package github.Louwind.Features.impl.procesor;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureProcessors;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldView;

import java.util.List;
import java.util.Random;

public class ContextRuleProcessor extends ContextProcessor {

    public static final Codec<ContextRuleProcessor> CODEC = Codec.unit(new ContextRuleProcessor(OptionalContextParameter.empty()));

    private final OptionalContextParameter<StructureProcessorRule[]> rules;

    public ContextRuleProcessor(OptionalContextParameter<StructureProcessorRule[]> rules) {
        this.rules = rules;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        Random random = new Random(MathHelper.hashCode(structureBlockInfo2.pos));
        BlockState blockState = worldView.getBlockState(structureBlockInfo2.pos);

        List<StructureProcessorRule> rules = Lists.newArrayList(this.rules.get(this.context));

        for (StructureProcessorRule rule : rules) {

            if(rule.test(structureBlockInfo2.state, blockState, structureBlockInfo.pos, structureBlockInfo2.pos, blockPos, random))
                return new Structure.StructureBlockInfo(structureBlockInfo2.pos, rule.getOutputState(), rule.getOutputNbt());

        }

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return FeatureProcessors.CONTEXT_RULE_PROCESSOR;
    }

    public static class Serializer implements JsonSerializer<ContextRuleProcessor> {

        @Override
        public void toJson(JsonObject json, ContextRuleProcessor object, JsonSerializationContext context) {

        }

        @Override
        public ContextRuleProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
            OptionalContextParameter<StructureProcessorRule[]> rules = FeaturesJsonHelper.getOptionalContextParameter(json, "rules", new StructureProcessorRule[] {}, element -> FeaturesJsonHelper.getProcessorRules(json, context, "rules"));

            return new ContextRuleProcessor(rules);
        }

    }

}
