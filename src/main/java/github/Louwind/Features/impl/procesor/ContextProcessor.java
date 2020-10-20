package github.Louwind.Features.impl.procesor;

import github.Louwind.Features.context.FeatureContext;
import net.minecraft.structure.processor.StructureProcessor;

public abstract class ContextProcessor extends StructureProcessor {

    protected FeatureContext context = FeatureContext.EMPTY;

    public FeatureContext getContext() {
        return this.context;
    }

    public void setContext(FeatureContext context) {
        this.context = context;
    }

}
