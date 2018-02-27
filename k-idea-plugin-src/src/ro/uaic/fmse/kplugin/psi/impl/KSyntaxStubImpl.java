package ro.uaic.fmse.kplugin.psi.impl;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import ro.uaic.fmse.kplugin.psi.IKSyntaxStub;
import ro.uaic.fmse.kplugin.psi.KSyntax;
import ro.uaic.fmse.kplugin.psi.KTypes;

/**
 * @author Denis Bogdanas
 * Created on 26-Feb-18.
 */
public class KSyntaxStubImpl extends StubBase<KSyntax> implements IKSyntaxStub {

    private String kSort;

    public KSyntaxStubImpl(final StubElement parent, String kSort) {
        super(parent, (IStubElementType) KTypes.SYNTAX);
        this.kSort = kSort;
    }

    @Override
    public String getSort() {
        return kSort;
    }
}
