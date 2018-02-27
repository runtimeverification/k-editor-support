package ro.uaic.fmse.kplugin.parsing;

import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.impl.source.tree.LightTreeUtil;
import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import ro.uaic.fmse.kplugin.KLanguage;
import ro.uaic.fmse.kplugin.psi.KSyntaxRhsRegular;
import ro.uaic.fmse.kplugin.psi.KSyntaxRhsRegularIndex;
import ro.uaic.fmse.kplugin.psi.KSyntaxRhsRegularStub;
import ro.uaic.fmse.kplugin.psi.KTypes;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxRhsRegularImpl;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxRhsRegularStubImpl;

import java.io.IOException;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KSyntaxRhsRegularStubElementType extends ILightStubElementType<KSyntaxRhsRegularStub, KSyntaxRhsRegular> {

    public KSyntaxRhsRegularStubElementType(@NotNull String debugName) {
        super(debugName, KLanguage.INSTANCE);
    }

    @Override
    public KSyntaxRhsRegularStub createStub(LighterAST tree, LighterASTNode node, StubElement parentStub) {
        LighterASTNode nameNode = LightTreeUtil.firstChildOfType(tree, node, KTypes.STRING);
        String name = nameNode != null && nameNode.getStartOffset() == node.getStartOffset()
                ? KSyntaxStubElementType.intern(tree.getCharTable(), nameNode) : null;
        return new KSyntaxRhsRegularStubImpl(parentStub, name);
    }

    @Override
    public KSyntaxRhsRegular createPsi(@NotNull KSyntaxRhsRegularStub stub) {
        return new KSyntaxRhsRegularImpl(stub, this);
    }

    @NotNull
    @Override
    public KSyntaxRhsRegularStub createStub(@NotNull KSyntaxRhsRegular psi, StubElement parentStub) {
        return new KSyntaxRhsRegularStubImpl(parentStub, psi.getName());
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "K." + toString();
    }

    @Override
    public void serialize(@NotNull KSyntaxRhsRegularStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public KSyntaxRhsRegularStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        final StringRef ref = dataStream.readName();
        String name = ref != null ? ref.getString() : null;
        return new KSyntaxRhsRegularStubImpl(parentStub, name);
    }

    @Override
    public void indexStub(@NotNull KSyntaxRhsRegularStub stub, @NotNull IndexSink sink) {
        if (stub.getName() != null) {
            sink.occurrence(KSyntaxRhsRegularIndex.KEY, stub.getName());
        }
    }
}
