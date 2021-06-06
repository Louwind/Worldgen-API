package github.Louwind.Features.util;

import com.google.gson.*;
import github.Louwind.Features.condition.FeatureCondition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.Optional;

public class FeaturesJsonHelper {

    public static Block getBlock(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return Registry.BLOCK.getOrEmpty(id).orElseThrow(() -> new JsonParseException("Expected " + id + " to be block, was unknown string '" + name + "'"));
    }

    public static FeatureCondition[] getConditions(JsonObject object, FeatureCondition[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureCondition[].class);
    }

    public static FeatureCondition[] getConditions(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getConditions(object, new FeatureCondition[]{}, context, name);
    }

    public static BlockState getBlockState(JsonObject object, String name) {
        JsonElement element = object.get(name);

        if(element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();

            if(primitive.isString()) {
                Block block = FeaturesJsonHelper.getBlock(object, name);

                return block.getDefaultState();
            }

        } else if(element.isJsonObject()) {
            JsonObject blockstate = object.getAsJsonObject(name);

            Block block = FeaturesJsonHelper.getBlock(blockstate, "block");
            BlockState state = block.getDefaultState();

            if(blockstate.has("properties")) {
                JsonObject properties = JsonHelper.getObject(blockstate, "properties");
                StateManager<?, ?> manager = block.getStateManager();

                for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
                    Property<?> property = manager.getProperty(entry.getKey());

                    if(property != null) {
                        JsonElement jsonElement = entry.getValue();

                        if(jsonElement.isJsonPrimitive()) {
                            JsonPrimitive primitive = jsonElement.getAsJsonPrimitive();

                            if(primitive.isString())
                                state = FeaturesJsonHelper.parsePropertyValue(property, state, primitive.getAsString());

                            if(primitive.isBoolean())
                                state = FeaturesJsonHelper.parsePropertyValue(property, state, String.valueOf(primitive.getAsBoolean()));

                            if(primitive.isNumber())
                                state = FeaturesJsonHelper.parsePropertyValue(property, state, String.valueOf(primitive.getAsInt()));
                        }

                    }

                }

            }

            return state;
        }

        return Blocks.AIR.getDefaultState();
    }

    @Deprecated
    private static <T extends Comparable<T>> BlockState parsePropertyValue(Property<T> property, BlockState state, String string) {
        Optional<T> optional = property.parse(string);

        if(optional.isPresent()) {
            T t = optional.get();

            return state.with(property, t);
        }

        return state;
    }

    public static Identifier getIdentifier(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return new Identifier(id);
    }

    public static BlockRotation getRotation(JsonObject object, String name) {
        String string = JsonHelper.getString(object, name);

        return BlockRotation.valueOf(string.toUpperCase());
    }

    public static Vec3d getVector(JsonObject object, String name) {
        double x = JsonHelper.getDouble(object, name, 0);
        double y = JsonHelper.getDouble(object, name, 0);
        double z = JsonHelper.getDouble(object, name, 0);

        return new Vec3d(x, y, z);
    }

}