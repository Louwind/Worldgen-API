package github.Louwind.Features.impl.properties;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;

import java.util.Arrays;
import java.util.List;

public class DefaultFeatureProperties implements FeatureProperties {

    public static final FeatureProperties EMPTY = new DefaultFeatureProperties(BlockBox.empty(), null, new BlockRotation[]{}, 1);

    private final BlockBox box;
    private final StructurePool pool;
    private final List<BlockRotation> rotations;
    private final int size;

    public DefaultFeatureProperties(BlockBox box, StructurePool pool, BlockRotation[] rotations, int size) {
        this.rotations = Arrays.asList(rotations);

        this.box = box;
        this.pool = pool;
        this.size = size;
    }

    @Override
    public BlockBox getBox() {
        return this.box;
    }

    @Override
    public StructurePool getPool() {
        return this.pool;
    }

    @Override
    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public static class Serializer implements JsonSerializer<DefaultFeatureProperties> {

        @Override
        public void toJson(JsonObject json, DefaultFeatureProperties object, JsonSerializationContext context) {
            // TODO toJson
        }

        @Override
        public DefaultFeatureProperties fromJson(JsonObject json, JsonDeserializationContext context) {
            int size = JsonHelper.getInt(json, "size");

            BlockBox box = FeaturesJsonHelper.getBox(json, "box");
            StructurePool pool = FeaturesJsonHelper.getStructurePool(json, "pool");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context,"rotations");

            return new DefaultFeatureProperties(box, pool, rotations, size);
        }

    }

}
