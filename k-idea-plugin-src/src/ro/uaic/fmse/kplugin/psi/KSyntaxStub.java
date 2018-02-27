package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.stubs.StubElement;

/**
 * @author Denis Bogdanas
 * Created on 26-Feb-18.
 */
public interface KSyntaxStub extends StubElement<KSyntax> {
    String getSort();
}
