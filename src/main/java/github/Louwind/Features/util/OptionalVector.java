package github.Louwind.Features.util;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import net.minecraft.util.math.Vec3d;

import java.util.NoSuchElementException;

public class OptionalVector {

    private static final OptionalVector EMPTY = new OptionalVector(OptionalContextParameter.empty());

    private OptionalContextParameter<Vec3d> pos = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> x = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> y = OptionalContextParameter.empty();
    private OptionalContextParameter<Double> z = OptionalContextParameter.empty();

    private OptionalVector(OptionalContextParameter<Double> x, OptionalContextParameter<Double> y, OptionalContextParameter<Double> z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private OptionalVector(OptionalContextParameter<Vec3d> pos) {
        this.pos = pos;
    }

    public Vec3d asVector(FeatureContext context) {

        if(this.pos.isPresent()) {
            Vec3d pos = this.pos.get(context);

            return pos;
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
            return "OptionalVector{pos=" + this.pos + "}";

        if(this.x.isPresent() || this.y.isPresent() || this.z.isPresent())
            return "OptionalVector{x=" + this.x + ", y=" + this.y + ", z=" + this.z + "}";

        return "OptionalVector.empty";
    }

    public static OptionalVector empty() {
        return EMPTY;
    }

    public static OptionalVector of(OptionalContextParameter<Vec3d> pos) {
        return new OptionalVector(pos);
    }

    public static OptionalVector of(Vec3d pos) {
        return new OptionalVector(OptionalContextParameter.of(pos));
    }

    public static OptionalVector of(OptionalContextParameter<Double> x, OptionalContextParameter<Double> y, OptionalContextParameter<Double> z) {
        return new OptionalVector(x, y, z);
    }

}
