package github.Louwind.Features.metadata;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;
import github.Louwind.Features.function.FeatureFunction;

import java.util.List;
import java.util.function.Consumer;

public interface FeatureMetadata extends FeatureContextPredicate, Consumer<FeatureContext> {

    List<FeatureFunction> getFunctions();

    FeatureMetadataType getType();

    @Override
    default boolean test(FeatureContext context) {
        return this.getConditions().stream().allMatch(condition -> condition.test(context));
    }

}
