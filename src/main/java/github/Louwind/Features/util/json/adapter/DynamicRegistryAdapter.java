package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import github.Louwind.Features.mixin.AccessorDynamicRegistryManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;

import java.lang.reflect.Type;

public abstract class DynamicRegistryAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {

    protected abstract T fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context);

    protected abstract JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, T src, Type typeOfSrc, JsonSerializationContext context);

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var resourceManager = MinecraftClient.getInstance().getResourceManager();
        var registryManager = AccessorDynamicRegistryManager.getRegistryManager();
        var registryOps = RegistryOps.method_36574(JsonOps.INSTANCE, resourceManager, registryManager);

        return this.fromJson(registryOps, json, typeOfT, context);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        var registryManager = AccessorDynamicRegistryManager.getRegistryManager();
        var registryReadingOps = RegistryReadingOps.of(JsonOps.INSTANCE, registryManager);

        return this.toJson(registryReadingOps, src, typeOfSrc, context);
    }

}