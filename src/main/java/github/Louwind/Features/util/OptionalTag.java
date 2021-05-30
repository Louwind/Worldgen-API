package github.Louwind.Features.util;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.NoSuchElementException;

public class OptionalTag {

    private static final OptionalTag EMPTY = new OptionalTag(OptionalContextParameter.empty());

    private OptionalContextParameter<NbtElement> tag;

    private OptionalTag(OptionalContextParameter<NbtElement> tag) {
        this.tag = tag;
    }

    public NbtCompound getCompound(FeatureContext context) {

        if(this.tag.isPresent())
            return (NbtCompound) this.tag.get(context);

        throw new NoSuchElementException("No parameter is present");
    }

    public static OptionalTag empty() {
        return EMPTY;
    }

    public static OptionalTag of(OptionalContextParameter<NbtElement> tag) {
        return new OptionalTag(tag);
    }

    public static OptionalTag of(NbtElement tag) {
        return new OptionalTag(OptionalContextParameter.of(tag));
    }

    public static OptionalTag newCompoundTag() {
        return OptionalTag.of(new NbtCompound());
    }

}
