package github.Louwind.Features.condition;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import net.minecraft.util.JsonSerializer;

import java.util.function.Consumer;

public class FeatureConditionType extends ContextAwareSerializableType<FeatureCondition> {

    public FeatureConditionType(JsonSerializer<? extends FeatureCondition> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
        super(jsonSerializer, consumer);
   }

}
