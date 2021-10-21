package github.Louwind.worldgen.util.structure.pool;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Function;

public class StructurePoolBuilder {

    protected Identifier id;
    protected Identifier terminatorsId;
    protected StructurePool.Projection projection;
    protected List<Pair<Function<StructurePool.Projection, ? extends StructurePoolElement>, Integer>> elements;

    public StructurePoolBuilder(String id) {
        this.id = Identifier.tryParse(id);
        this.terminatorsId = Identifier.tryParse("empty");
        this.projection = StructurePool.Projection.RIGID;
        this.elements = Lists.newArrayList();
    }

    public StructurePoolBuilder projection(StructurePool.Projection projection) {
        this.projection = projection;
        return this;
    }

    public StructurePoolBuilder emptyElement(int weight) {
        return this.element(StructurePoolElement.ofEmpty(), weight);
    }

    public StructurePoolBuilder singleElement(String id, int weight) {
        return this.element(StructurePoolElement.ofSingle(id), weight);
    }

    public StructurePoolBuilder singleElement(String id, StructureProcessorList processors, int weight) {
        return this.element(StructurePoolElement.ofProcessedSingle(id, processors), weight);
    }

    public StructurePoolBuilder featureElement(ConfiguredFeature<?, ?> feature, int weight) {
        return this.element(StructurePoolElement.ofFeature(feature), weight);
    }

    public StructurePoolBuilder legacyElement(String id, int weight) {
        return this.element(StructurePoolElement.ofLegacySingle(id), weight);
    }

    public StructurePoolBuilder legacyElement(String id, StructureProcessorList processors, int weight) {
        return this.element(StructurePoolElement.ofProcessedLegacySingle(id, processors), weight);
    }

    public StructurePoolBuilder listElement(List<Function<StructurePool.Projection, ? extends StructurePoolElement>> list, int weight) {
        return this.element(StructurePoolElement.ofList(list), weight);
    }

    public StructurePoolBuilder element(Function<StructurePool.Projection, ? extends StructurePoolElement> element, int weight) {
        this.elements.add(Pair.of(element, weight));
        return this;
    }

    public StructurePool build() {

        if(this.id == null)
            throw new IllegalArgumentException("Structure pool id can't be null!");

        return new StructurePool(this.id, this.terminatorsId, this.elements, this.projection);
    }

}
