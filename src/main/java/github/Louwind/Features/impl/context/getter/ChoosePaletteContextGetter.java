package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.RANDOM;

public class ChoosePaletteContextGetter implements FeatureContextGetter<StructureProcessorRule[]> {

    private final List<FeatureCondition> conditions;
    private final List<StructureProcessorRule[]> palettes;

    public ChoosePaletteContextGetter(List<StructureProcessorRule[]> palettes, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.palettes = palettes;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.CHOOSE_PALETTE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public StructureProcessorRule[] apply(FeatureContext context) {
        Random random = context.get(RANDOM);

        int size = this.palettes.size();
        int index = random.nextInt(size);

        return this.palettes.get(index);
    }

    public static class Serializer implements JsonSerializer<ChoosePaletteContextGetter> {

        @Override
        public void toJson(JsonObject json, ChoosePaletteContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public ChoosePaletteContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");
            List<StructureProcessorRule[]> palettes = FeaturesJsonHelper.getWeightedList(json, "palettes", obj -> FeaturesJsonHelper.getProcessorRules(obj, context, "rules"));

            return new ChoosePaletteContextGetter(palettes, conditions);
        }

    }

}
