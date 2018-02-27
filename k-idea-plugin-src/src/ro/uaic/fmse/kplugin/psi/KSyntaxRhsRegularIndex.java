package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KSyntaxRhsRegularIndex extends StringStubIndexExtension<KSyntaxRhsRegular> {
    public static final StubIndexKey<String, KSyntaxRhsRegular> KEY =
            StubIndexKey.createIndexKey("k.syntax_rhs_regular.index");

    public static final KSyntaxRhsRegularIndex INSTANCE = new KSyntaxRhsRegularIndex();

    @NotNull
    @Override
    public StubIndexKey<String, KSyntaxRhsRegular> getKey() {
        return KEY;
    }
}
