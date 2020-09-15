package github.Louwind.Features.context.getter;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

public interface StructureContextGetter<T> {
	
	void get(FeatureContextParameter<T> parameter, FeatureContext.Builder builder);
	
}
