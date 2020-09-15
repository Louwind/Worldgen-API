package github.Louwind.Features.context.parameter;

import net.minecraft.util.Identifier;

public class FeatureContextParameter<T> {

	private final Identifier id;

	public FeatureContextParameter(Identifier id) {
		this.id = id;
	}

	public Identifier getIdentifier() {
		return this.id;
	}

	@Override
	public String toString() {
		return "<Feature Parameter " + this.id + ">";
	}

}
