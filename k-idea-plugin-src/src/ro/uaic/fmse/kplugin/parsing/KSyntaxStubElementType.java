package ro.uaic.fmse.kplugin.parsing;

import com.intellij.lang.LighterAST;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.LighterASTTokenNode;
import com.intellij.psi.impl.source.tree.LightTreeUtil;
import com.intellij.psi.stubs.*;
import com.intellij.util.CharTable;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ro.uaic.fmse.kplugin.KLanguage;
import ro.uaic.fmse.kplugin.psi.KSyntax;
import ro.uaic.fmse.kplugin.psi.KSyntaxSortIndex;
import ro.uaic.fmse.kplugin.psi.KSyntaxStub;
import ro.uaic.fmse.kplugin.psi.KTypes;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxImpl;
import ro.uaic.fmse.kplugin.psi.impl.KSyntaxStubImpl;

import java.io.IOException;

/**
 * @author Denis Bogdanas
 * Created on 26-Feb-18.
 */
public class KSyntaxStubElementType extends ILightStubElementType<KSyntaxStub, KSyntax> {

    public KSyntaxStubElementType(@NotNull @NonNls String debugName) {
        super(debugName, KLanguage.INSTANCE);
    }

    @Override
    public KSyntaxStub createStub(LighterAST tree, LighterASTNode node, StubElement parentStub) {
        LighterASTNode sortNode = LightTreeUtil.firstChildOfType(tree, node, KTypes.SORT);
        String sort = intern(tree.getCharTable(), sortNode);
        return new KSyntaxStubImpl(parentStub, sort);
    }

    @Override
    public KSyntax createPsi(@NotNull KSyntaxStub kSyntaxStub) {
        return new KSyntaxImpl(kSyntaxStub, this);
    }

    @NotNull
    @Override
    public KSyntaxStub createStub(@NotNull KSyntax kSyntax, StubElement parentStub) {
        return new KSyntaxStubImpl(parentStub, kSyntax.getName());
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "K." + toString();
    }

    @Override
    public void serialize(@NotNull KSyntaxStub stub, @NotNull StubOutputStream stubOutputStream)
            throws IOException {
        stubOutputStream.writeName(stub.getSort());
    }

    @NotNull
    @Override
    public KSyntaxStub deserialize(@NotNull StubInputStream stubInputStream, StubElement parentStub)
            throws IOException {
        final StringRef ref = stubInputStream.readName();
        return new KSyntaxStubImpl(parentStub, ref.getString());
    }

    @Override
    public void indexStub(@NotNull KSyntaxStub stub, @NotNull IndexSink indexSink) {
        indexSink.occurrence(KSyntaxSortIndex.KEY, stub.getSort());
    }

    public static String intern(@NotNull CharTable table, @NotNull LighterASTNode node) {
        assert node instanceof LighterASTTokenNode : node;
        return table.intern(((LighterASTTokenNode) node).getText()).toString();
    }
}
