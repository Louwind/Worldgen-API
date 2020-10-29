package github.Louwind.Features.util;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.NoSuchElementException;

public class OptionalBlockPos {

    private static final OptionalBlockPos EMPTY = new OptionalBlockPos(OptionalContextParameter.empty());

    private OptionalContextParameter<BlockPos> pos = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> x = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> y = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> z = OptionalContextParameter.empty();

    private OptionalBlockPos(OptionalContextParameter<Double> x, OptionalContextParameter<Double> y, OptionalContextParameter<Double> z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private OptionalBlockPos(OptionalContextParameter<BlockPos> pos) {
        this.pos = pos;
    }

    public BlockPos asPosition(FeatureContext context) {

        if(this.pos.isPresent())
            return this.pos.get(context);

        if(this.x.isPresent() || this.y.isPresent() || this.z.isPresent()) {
            double x = this.x.isPresent() ? this.x.get(context) : 0;
            double y = this.y.isPresent() ? this.y.get(context) : 0;
            double z = this.z.isPresent() ? this.z.get(context) : 0;

            return new BlockPos(x, y, z);
        }

        throw new NoSuchElementException("No parameter nor values are present");
    }

    public Vec3d asVector(FeatureContext context) {

        if(this.pos.isPresent()) {
            BlockPos pos = this.pos.get(context);

            return Vec3d.of(pos);
        }

        if(this.x.isPresent() || this.y.isPresent() || this.z.isPresent()) {
            double x = this.x.isPresent() ? this.x.get(context) : 0;
            double y = this.y.isPresent() ? this.y.get(context) : 0;
            double z = this.z.isPresent() ? this.z.get(context) : 0;

            return new Vec3d(x, y, z);
        }

        throw new NoSuchElementException("No parameter nor values are present");
    }

    public boolean isPresent() {
        return this.pos.isPresent() || this.x.isPresent() || this.y.isPresent() || this.z.isPresent();
    }

    @Override
    public String toString() {

        if(this.pos.isPresent())
            return "OptionalBlockPos{pos=" + this.pos + "}";

        if(this.x.isPresent() || this.y.isPresent() || this.z.isPresent())
            return "OptionalBlockPos{x=" + this.x + ", y=" + this.y + ", z=" + this.z + "}";

        return "OptionalBlockPos.empty";
    }

    public static OptionalBlockPos empty() {
        return EMPTY;
    }

    public static OptionalBlockPos of(OptionalContextParameter<BlockPos> pos) {
        return new OptionalBlockPos(pos);
    }

    public static OptionalBlockPos of(BlockPos pos) {
        return new OptionalBlockPos(OptionalContextParameter.of(pos));
    }

    public static OptionalBlockPos of(OptionalContextParameter<Double> x, OptionalContextParameter<Double> y, OptionalContextParameter<Double> z) {
        return new OptionalBlockPos(x, y, z);
    }

}
