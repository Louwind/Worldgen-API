package github.Louwind.Features.impl.context.override;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.override.FeatureContextOverrideType;
import github.Louwind.Features.impl.init.FeatureContextOverrides;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;

public class ParameterContextOverride<T> implements FeatureContextOverride {

    protected final List<FeatureContextGetter<T>> from;
    protected final FeatureContextParameter<T> parameter;

    public ParameterContextOverride(FeatureContextParameter<T> parameter, FeatureContextGetter<T>[] from) {
        this.from = Arrays.asList(from);
        this.parameter = parameter;
    }

    @Override
    public void accept(FeatureContextProvider provider, FeatureContextBuilder builder) {

        for (FeatureContextGetter<T> from : this.from) {

            try {
                FeatureContext context = builder.build(provider.getType());

                if (from.test(context)) {
                    T t = from.apply(context);

                    builder.put(this.parameter, t);

                    return;
                }

            } catch (IllegalArgumentException e) {
                String message = e.getMessage();

                LogManager.getLogger().warn(message);
            }

        }

    }

    @Override
    public FeatureContextOverrideType getType() {
        return FeatureContextOverrides.PARAMETER;
    }

    public static class Serializer implements JsonSerializer<ParameterContextOverride> {

        @Override
        public void toJson(JsonObject json, ParameterContextOverride object, JsonSerializationContext context) {

        }

        @Override
        public ParameterContextOverride fromJson(JsonObject json, JsonDeserializationContext context) {
            JsonObject object = json.getAsJsonObject();

            FeatureContextGetter[] from = FeaturesJsonHelper.getFrom(object, context, "from");
            FeatureContextParameter parameter = FeaturesJsonHelper.getContextParameter(object, "parameter");

            return new ParameterContextOverride(parameter, from);
        }

    }

}
