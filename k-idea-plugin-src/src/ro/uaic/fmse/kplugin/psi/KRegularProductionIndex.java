package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KRegularProductionIndex extends StringStubIndexExtension<KRegularProduction> {
    public static final StubIndexKey<String, KRegularProduction> KEY =
            StubIndexKey.createIndexKey("k.regular_production.index");

    public static final KRegularProductionIndex INSTANCE = new KRegularProductionIndex();

    @NotNull
    @Override
    public StubIndexKey<String, KRegularProduction> getKey() {
        return KEY;
    }
}
