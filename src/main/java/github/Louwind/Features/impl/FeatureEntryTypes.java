package github.Louwind.Features.impl;

import github.Louwind.Features.entry.FeatureEntryType;
import github.Louwind.Features.impl.entry.GenericFeatureEntry;

public class FeatureEntryTypes {

    public static final FeatureEntryType ENTRY = new FeatureEntryType(new GenericFeatureEntry.Serializer());

}
