package ro.uaic.fmse.kplugin.parsing;

import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.impl.source.tree.LightTreeUtil;
import com.intellij.psi.stubs.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;
import ro.uaic.fmse.kplugin.KLanguage;
import ro.uaic.fmse.kplugin.psi.*;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxRhsAuxFunctionImpl;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxRhsRegularImpl;
import ro.uaic.fmse.kplugin.psi.impl.KRegularProductionStubImpl;

import java.io.IOException;

/**
 * @author Denis Bogdanas
 * Created on 27-Feb-18.
 */
public class KSyntaxRhsRegularStubElementType extends ILightStubElementType<KRegularProductionStub, KRegularProduction> {

    public KSyntaxRhsRegularStubElementType(@NotNull String debugName) {
        super(debugName, KLanguage.INSTANCE);
    }

    @Override
    public KRegularProductionStub createStub(LighterAST tree, LighterASTNode node, StubElement parentStub) {
        IElementType nameChildType;
        boolean auxFunction;
        if (node.getTokenType() == KTypes.SYNTAX_RHS_AUX_FUNCTION) {
            nameChildType = KTypes.ID;
            auxFunction = true;
        } else {
            nameChildType = KTypes.STRING;
            auxFunction = false;
        }
        LighterASTNode nameNode = LightTreeUtil.firstChildOfType(tree, node, nameChildType);
        String name = nameNode != null && nameNode.getStartOffset() == node.getStartOffset()
                ? KSyntaxStubElementType.intern(tree.getCharTable(), nameNode) : null;
        return new KRegularProductionStubImpl(parentStub, name, auxFunction);
    }

    @Override
    public KRegularProduction createPsi(@NotNull KRegularProductionStub stub) {
        return stub.isAuxFunction()
                ? new KSyntaxRhsAuxFunctionImpl(stub, (IStubElementType) KTypes.SYNTAX_RHS_AUX_FUNCTION)
                : new KSyntaxRhsRegularImpl(stub, (IStubElementType) KTypes.SYNTAX_RHS_REGULAR);
    }

    @NotNull
    @Override
    public KRegularProductionStub createStub(@NotNull KRegularProduction psi, StubElement parentStub) {
        return new KRegularProductionStubImpl(parentStub, psi.getName(), psi instanceof KSyntaxRhsAuxFunction);
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "K." + toString();
    }

    @Override
    public void serialize(@NotNull KRegularProductionStub stub, @NotNull StubOutputStream dataStream)
            throws IOException {
        dataStream.writeName(stub.getName());
        dataStream.writeBoolean(stub.isAuxFunction());
    }

    @NotNull
    @Override
    public KRegularProductionStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub)
            throws IOException {
        final StringRef ref = dataStream.readName();
        boolean auxFunction = dataStream.readBoolean();
        String name = ref != null ? ref.getString() : null;
        return new KRegularProductionStubImpl(parentStub, name, auxFunction);
    }

    @Override
    public void indexStub(@NotNull KRegularProductionStub stub, @NotNull IndexSink sink) {
        if (stub.getName() != null) {
            sink.occurrence(KRegularProductionIndex.KEY, stub.getName());
        }
    }
}
