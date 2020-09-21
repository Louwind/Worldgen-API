package github.Louwind.Features.impl.properties;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.FeaturePropertiesTypes;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.properties.FeaturePropertiesType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureProperties implements FeatureProperties {

    public static final FeatureProperties EMPTY = new GenericFeatureProperties(BlockBox.empty(), new BlockRotation[]{}, 1);

    private final BlockBox box;
    private final List<BlockRotation> rotations;
    private final int size;

    public GenericFeatureProperties(BlockBox box, BlockRotation[] rotations, int size) {
        this.rotations = Arrays.asList(rotations);

        this.box = box;
        this.size = size;
    }

    @Override
    public BlockBox getBox() {
        return this.box;
    }

    @Override
    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public FeaturePropertiesType getType() {
        return FeaturePropertiesTypes.PROPERTIES;
    }

    public static class Serializer implements JsonSerializer<GenericFeatureProperties> {

        @Override
        public void toJson(JsonObject json, GenericFeatureProperties object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public GenericFeatureProperties fromJson(JsonObject json, JsonDeserializationContext context) {
            int size = JsonHelper.getInt(json, "size");

            BlockBox box = FeaturesJsonHelper.getBox(json, "box");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context,"rotations");

            return new GenericFeatureProperties(box, rotations, size);
        }

    }

}
