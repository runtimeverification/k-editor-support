package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.stubs.StubElement;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public interface KRegularProductionStub extends StubElement<KRegularProduction> {
    String getName();

    boolean isAuxFunction();
}
