package github.Louwind.Features.context;

import github.Louwind.Features.condition.FeatureCondition;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.function.Predicate;

public interface FeatureContextPredicate extends FeatureContextAware, Predicate<FeatureContext> {

    List<FeatureCondition> getConditions();

    @Override
    default boolean test(FeatureContext context) {

        try {
            return this.hasRequiredParameters(context) && this.getConditions().stream().allMatch(condition -> condition.test(context));
        } catch (IllegalAccessException e) {
            String message = e.getMessage();

            LogManager.getLogger().warn(message);
        }

        return false;
    }

}
