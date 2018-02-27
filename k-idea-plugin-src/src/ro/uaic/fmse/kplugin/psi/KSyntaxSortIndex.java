package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KSyntaxSortIndex extends StringStubIndexExtension<KSyntax> {

    public static final StubIndexKey<String, KSyntax> KEY = StubIndexKey.createIndexKey("k.syntax.index");

    public static final KSyntaxSortIndex INSTANCE = new KSyntaxSortIndex();

    @NotNull
    @Override
    public StubIndexKey<String, KSyntax> getKey() {
        return KEY;
    }
}
