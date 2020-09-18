package github.Louwind.Features.entry;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureEntryType extends JsonSerializableType<FeatureEntry> {
    public FeatureEntryType(JsonSerializer<? extends FeatureEntry> jsonSerializer) {
        super(jsonSerializer);
    }

}
