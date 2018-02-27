package ro.uaic.fmse.kplugin.psi.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import ro.uaic.fmse.kplugin.psi.KSyntaxRhsRegular;
import ro.uaic.fmse.kplugin.psi.KSyntaxRhsRegularStub;
import ro.uaic.fmse.kplugin.psi.KTypes;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KSyntaxRhsRegularStubImpl extends StubBase<KSyntaxRhsRegular> implements KSyntaxRhsRegularStub {

    private String name;

    public KSyntaxRhsRegularStubImpl(StubElement parent, String name) {
        super(parent, (IStubElementType) KTypes.SYNTAX_RHS_REGULAR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
