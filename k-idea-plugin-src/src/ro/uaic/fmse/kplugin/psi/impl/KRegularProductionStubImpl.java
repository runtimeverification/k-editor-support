package ro.uaic.fmse.kplugin.psi.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import ro.uaic.fmse.kplugin.psi.KRegularProduction;
import ro.uaic.fmse.kplugin.psi.KRegularProductionStub;
import ro.uaic.fmse.kplugin.psi.KTypes;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KRegularProductionStubImpl extends StubBase<KRegularProduction> implements KRegularProductionStub {

    private String name;
    private boolean auxFunction;

    public KRegularProductionStubImpl(StubElement parent, String name, boolean auxFunction) {
        super(parent, (IStubElementType) KTypes.SYNTAX_RHS_REGULAR);
        this.name = name;
        this.auxFunction = auxFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAuxFunction() {
        return auxFunction;
    }
}
