package github.Louwind.Features.impl.init;

import github.Louwind.Features.entry.FeatureEntryType;
import github.Louwind.Features.impl.entry.GenericFeatureEntry;

public class FeatureEntries {

    public static final FeatureEntryType ENTRY = new FeatureEntryType(new GenericFeatureEntry.Serializer());

}
