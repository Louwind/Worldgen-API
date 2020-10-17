package github.Louwind.Features.util;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.math.BlockPos;

import java.util.NoSuchElementException;

public class OptionalTag {

    private static final OptionalTag EMPTY = new OptionalTag(OptionalContextParameter.empty());

    private OptionalContextParameter<Tag> tag;

    private OptionalTag(OptionalContextParameter<Tag> tag) {
        this.tag = tag;
    }

    public CompoundTag get(FeatureContext context) {

        if(this.tag.isPresent())
            return (CompoundTag) this.tag.get(context);

        throw new NoSuchElementException("No parameter is present");
    }

    public static OptionalTag empty() {
        return EMPTY;
    }

    public static OptionalTag of(OptionalContextParameter<Tag> tag) {
        return new OptionalTag(tag);
    }

}
