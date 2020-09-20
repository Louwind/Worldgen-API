package github.Louwind.Features.context;

import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.impl.context.DefaultFeatureContext;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.function.Predicate;

public interface FeatureContextPredicate extends FeatureContextAware, Predicate<DefaultFeatureContext> {

    List<FeatureCondition> getConditions();

    @Override
    default boolean test(DefaultFeatureContext context) {

        try {
            return this.hasRequiredParameters(context) && this.getConditions().stream().allMatch(condition -> condition.test(context));
        } catch (IllegalAccessException e) {
            String message = e.getMessage();

            LogManager.getLogger().warn(message);
        }

        return false;
    }

}
