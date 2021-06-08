package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.block.BlockState;

import java.lang.reflect.Type;

import static net.minecraft.util.registry.Registry.BLOCK;

public class BlockStateAdapter implements JsonDeserializer<BlockState>, JsonSerializer<BlockState> {

    private static final Codec<BlockState> CODEC = BLOCK.dispatch("name", BlockState::getBlock, (object) -> {
        var state = object.getDefaultState();

        return state.getEntries().isEmpty() ? Codec.unit(state) : state.codec.fieldOf("properties").codec();
    });

    @Override
    public BlockState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return CODEC.parse(JsonOps.INSTANCE, json).get().orThrow();
    }

    @Override
    public JsonElement serialize(BlockState src, Type typeOfSrc, JsonSerializationContext context) {
        return CODEC.encodeStart(JsonOps.INSTANCE, src).get().orThrow();
    }

}