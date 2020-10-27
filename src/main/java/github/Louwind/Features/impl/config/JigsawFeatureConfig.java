package github.Louwind.Features.impl.config;

import com.google.common.base.Suppliers;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.config.FeatureConfigType;
import github.Louwind.Features.config.FeaturesConfig;
import github.Louwind.Features.impl.init.FeatureConfigTypes;
import github.Louwind.Features.util.CodecHelper;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class JigsawFeatureConfig extends FeaturesConfig {

    public static final Codec<BlockRotation> BLOCK_ROTATION_CODEC = CodecHelper.createEnum(BlockRotation::values);

    public static final Codec<JigsawFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(JigsawFeatureConfig::getStartPool),
            Codec.list(BLOCK_ROTATION_CODEC).fieldOf("rotations").forGetter(JigsawFeatureConfig::getRotations),
            Codec.BOOL.fieldOf("keepJigsaws").forGetter(JigsawFeatureConfig::getKeepJigsaws),
            Codec.BOOL.fieldOf("surface").forGetter(JigsawFeatureConfig::isSurface),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("size").forGetter(JigsawFeatureConfig::getSize))
            .apply(instance, JigsawFeatureConfig::new));

    protected final boolean keepJigsaws;
    protected final boolean surface;

    public JigsawFeatureConfig(Supplier<StructurePool> startPool, List<BlockRotation> rotations, boolean keepJigsaws, boolean surface, int size) {
        super(startPool, rotations, size);

        this.keepJigsaws = keepJigsaws;
        this.surface = surface;
    }

    public boolean getKeepJigsaws() {
        return this.keepJigsaws;
    }

    @Override
    public FeatureConfigType getType() {
        return FeatureConfigTypes.JIGSAW;
    }

    public boolean isSurface() {
        return this.surface;
    }

    public static class Serializer implements JsonSerializer<JigsawFeatureConfig> {

        @Override
        public void toJson(JsonObject json, JigsawFeatureConfig object, JsonSerializationContext context) {

        }

        @Override
        public JigsawFeatureConfig fromJson(JsonObject json, JsonDeserializationContext context) {
            StructurePool pool = FeaturesJsonHelper.getStructurePool(json, "start_pool");
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, "rotations");

            boolean keepJigsaws = JsonHelper.getBoolean(json, "keep_jigsaws", false);
            boolean surface = JsonHelper.getBoolean(json, "surface", true);
            int size = JsonHelper.getInt(json, "size", 1);

            return new JigsawFeatureConfig(Suppliers.ofInstance(pool), Arrays.asList(rotations), keepJigsaws, surface, size);
        }

    }

}
