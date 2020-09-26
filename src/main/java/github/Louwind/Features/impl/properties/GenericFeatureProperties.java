package github.Louwind.Features.impl.properties;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.FeatureProperties;
import github.Louwind.Features.properties.FeaturePropertiesType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureProperties implements github.Louwind.Features.properties.FeatureProperties {

    public static final GenericFeatureProperties EMPTY = new GenericFeatureProperties(new BlockRotation[]{});

    private final List<BlockRotation> rotations;

    public GenericFeatureProperties(BlockRotation[] rotations) {
        this.rotations = Arrays.asList(rotations);
    }

    @Override
    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    @Override
    public FeaturePropertiesType getType() {
        return FeatureProperties.PROPERTIES;
    }

    public static class Serializer implements JsonSerializer<GenericFeatureProperties> {

        @Override
        public void toJson(JsonObject json, GenericFeatureProperties object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureProperties fromJson(JsonObject json, JsonDeserializationContext context) {
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context, "rotations");

            return new GenericFeatureProperties(rotations);
        }

    }

}
