package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Type;

public class Vec3dAdapter implements JsonDeserializer<Vec3d>, JsonSerializer<Vec3d> {

    @Override
    public Vec3d deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        var x = JsonHelper.getDouble(object, "x", 0);
        var y = JsonHelper.getDouble(object, "y", 0);
        var z = JsonHelper.getDouble(object, "z", 0);

        return new Vec3d(x, y, z);
    }

    @Override
    public JsonElement serialize(Vec3d src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("x", src.x);
        object.addProperty("y", src.y);
        object.addProperty("z", src.z);

        return object;
    }

}