package github.Louwind.Features.context.setter;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.StructureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

public class FeatureContextSetter {

	protected final StructureContextGetter<Object>[] getters;
	protected final FeatureContextParameter<Object> parameter;

	public FeatureContextSetter(FeatureContextParameter<Object> parameter, StructureContextGetter<Object>[] getters) {
		this.getters = getters;
		this.parameter = parameter;
	}

	public void set(FeatureContext.Builder builder) {
		FeatureContext context = builder.build();

		for (StructureContextGetter<Object> getter : this.getters)
			getter.get(this.parameter, builder);

	}
	
}
