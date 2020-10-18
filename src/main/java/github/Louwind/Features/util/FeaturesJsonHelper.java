package github.Louwind.Features.util;

import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.context.provider.PieceContextProvider;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.registry.FeaturesRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.nbt.Tag;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FeaturesJsonHelper {

    public static Block getBlock(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return Registry.BLOCK.get(id);
    }

    public static EntityType<?> getEntityType(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return Registry.ENTITY_TYPE.get(id);
    }

    public static <T extends Enum> T getEnum(JsonObject object, Class<T>  clazz, String name) {
        String string = JsonHelper.getString(object, name);

        return (T) Enum.valueOf(clazz, string.toUpperCase());
    }

    public static Identifier getIdentifier(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return new Identifier(id);
    }

    public static StructurePool.Projection getProjection(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return StructurePool.Projection.getById(id);
    }

    public static FeatureCondition[] getConditions(JsonObject object, FeatureCondition[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureCondition[].class);
    }

    public static FeatureCondition[] getConditions(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getConditions(object, new FeatureCondition[]{}, context, name);
    }

    public static FeatureContextParameter getContextParameter(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return FeaturesRegistry.FEATURE_CONTEXT_PARAMETER.get(id);
    }

    public static FeatureContextProvider getContextProvider(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, PieceContextProvider.EMPTY, context, FeatureContextProvider.class);
    }

    public static <T> OptionalContextParameter<T> getOptionalContextParameter(JsonObject object, String name, T defaultValue, Function<JsonElement, T> function) {

        if(object.has(name)) {
            JsonElement element = object.get(name);

            if(element.isJsonObject()) {
                FeatureContextParameter<T> parameter = FeaturesJsonHelper.getContextParameter(element.getAsJsonObject(), "parameter");

                return OptionalContextParameter.of(parameter);
            }

            return OptionalContextParameter.of(function.apply(element));
        }

        return defaultValue != null ? OptionalContextParameter.of(defaultValue) : OptionalContextParameter.empty();
    }

    public static OptionalBlockPos getOptionalBlockPos(JsonObject object, String name) {

        if(object.has(name)) {
            JsonElement element = object.get(name);

            if(element.isJsonObject()) {
                JsonObject pos = element.getAsJsonObject();

                if(pos.has("parameter")) {
                    FeatureContextParameter<BlockPos> parameter = FeaturesJsonHelper.getContextParameter(pos, "parameter");
                    OptionalContextParameter<BlockPos> optional = OptionalContextParameter.of(parameter);

                    return OptionalBlockPos.of(optional);
                }

                if(pos.has("x") || pos.has("y") || pos.has("z")) {
                    OptionalContextParameter<Integer> x = FeaturesJsonHelper.getOptionalInt(pos, "x");
                    OptionalContextParameter<Integer> y = FeaturesJsonHelper.getOptionalInt(pos, "y");
                    OptionalContextParameter<Integer> z = FeaturesJsonHelper.getOptionalInt(pos, "z");

                    return OptionalBlockPos.of(x, y, z);
                }

            }

        }

        return OptionalBlockPos.of(BlockPos.ORIGIN);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum> OptionalContextParameter<T> getOptionalEnumContextParameter(JsonObject object, String name, T defaultValue, Class<T> clazz) {
        return (OptionalContextParameter<T>) FeaturesJsonHelper.getOptionalContextParameter(object, name, defaultValue, jsonElement -> FeaturesJsonHelper.getEnum(object, defaultValue.getClass(), name));
    }

    public static OptionalContextParameter<Integer> getOptionalInt(JsonObject object, String name) {
        return FeaturesJsonHelper.getOptionalContextParameter(object, name, 0, JsonElement::getAsInt);
    }

    public static OptionalContextParameter<Float> getOptionalFloat(JsonObject object, String name) {
        return FeaturesJsonHelper.getOptionalContextParameter(object, name, 0f, JsonElement::getAsFloat);
    }

    public static OptionalContextParameter<BlockRotation> getOptionalRotation(JsonObject object, String name) {
        return FeaturesJsonHelper.getOptionalEnumContextParameter(object, name, BlockRotation.NONE, BlockRotation.class);
    }

    public static OptionalTag getOptionalTag(JsonObject object, String name) {

        if(object.has(name)) {
            JsonElement element = object.get(name);

            if(element.isJsonObject()) {
                JsonObject tag = element.getAsJsonObject();

                if(tag.has("parameter")) {
                    FeatureContextParameter<Tag> parameter = FeaturesJsonHelper.getContextParameter(tag, "parameter");
                    OptionalContextParameter<Tag> optional = OptionalContextParameter.of(parameter);

                    return OptionalTag.of(optional);
                }

            }

            if(element.isJsonPrimitive()) {
                JsonPrimitive primitive = element.getAsJsonPrimitive();

                if(primitive.isString()) {
                    try {
                        CompoundTag compoundTag = StringNbtReader.parse(primitive.getAsString());
                        OptionalContextParameter<Tag> optional = OptionalContextParameter.of(compoundTag);

                        return OptionalTag.of(optional);
                    } catch (CommandSyntaxException e) {
                        throw new JsonSyntaxException(e.getMessage());
                    }
                }

            }

        }

        return OptionalTag.newCompoundTag();
    }

    public static FeatureEntry[] getEntries(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, new FeatureEntry[]{}, context, FeatureEntry[].class);
    }

    public static FeatureContextGetter[] getFrom(JsonObject object, FeatureContextGetter[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, "from", defaultValue, context, FeatureContextGetter[].class);
    }

    public static FeatureContextGetter[] getFrom(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getFrom(object, new FeatureContextGetter[]{}, context, name);
    }

    public static FeatureFunction[] getFunction(JsonObject object, FeatureFunction[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureFunction[].class);
    }

    public static FeatureFunction[] getFunction(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getFunction(object, new FeatureFunction[]{}, context, name);
    }

    public static StructureProcessorList getProcessors(JsonObject object, StructureProcessor[] defaultValue, JsonDeserializationContext context, String name) {

        if(object.has(name)) {
            JsonElement element = object.get(name);

            if(element.isJsonPrimitive()) {
                JsonPrimitive primitive = element.getAsJsonPrimitive();

                if(primitive.isString()) {
                    Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

                    return BuiltinRegistries.STRUCTURE_PROCESSOR_LIST.get(id);
                }

            }
        }

        StructureProcessor[] processors = JsonHelper.deserialize(object, name, defaultValue, context, StructureProcessor[].class);

        return new StructureProcessorList(Arrays.asList(processors));
    }

    public static StructureProcessorList getProcessors(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getProcessors(object, new StructureProcessor[]{}, context, name);
    }

    public static BlockRotation[] getRotations(JsonObject object, JsonDeserializationContext context, String name) {

        if(object.has(name)) {
            JsonElement element = object.get(name);

            if (element.isJsonPrimitive()) {
                JsonPrimitive primitive = element.getAsJsonPrimitive();

                if (primitive.isString()) {
                    String string = primitive.getAsString();

                    if (string.equals("all"))
                        return BlockRotation.values();

                    BlockRotation rotation = FeaturesJsonHelper.getEnum(object, BlockRotation.class, name);

                    if(rotation != null)
                        return new BlockRotation[] { rotation };
                }
            }
        }

        return new BlockRotation[]{};
    }

    public static FeatureContextOverride[] getContextOverrides(JsonObject object, FeatureContextOverride[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureContextOverride[].class);
    }

    public static FeatureContextOverride[] getContextOverrides(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getContextOverrides(object, new FeatureContextOverride[]{}, context, name);
    }

    public static StructurePool getStructurePool(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return BuiltinRegistries.STRUCTURE_POOL.get(id);
    }

    public static FeaturePool[] getPools(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, new FeaturePool[]{}, context, FeaturePool[].class);
    }

    public static StructureProcessorRule[] getProcessorRules(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getProcessorRules(object, name, new StructureProcessorRule[]{}, context);
    }

    public static StructureProcessorRule[] getProcessorRules(JsonObject object, String name, StructureProcessorRule[] defaultValue, JsonDeserializationContext context) {
        return JsonHelper.deserialize(object, name, defaultValue, context, StructureProcessorRule[].class);
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

}
