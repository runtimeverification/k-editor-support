// Copyright (c) 2014 K Team. All Rights Reserved.
package ro.uaic.fmse.kplugin.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.uaic.fmse.kplugin.psi.*;

/**
 * @author Denis Bogdanas
 * Created on 12/15/13.
 */
@SuppressWarnings("WeakerAccess")
public class KSyntaxBase extends StubBasedPsiElementBase<KSyntaxStub> implements IKSyntaxBase {
    public KSyntaxBase(@NotNull ASTNode node) {
        super(node);
    }

    public KSyntaxBase(final KSyntaxStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    @Nullable
    @Override
    public String getName() {
        final KSyntaxStub stub = getStub();
        if (stub != null) {
            return stub.getSort();
        }

        return ((KSyntax) this).getSort().getText();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
        KSort sort = ((KSyntax) this).getSort();
        KSort newSort = KElementFactory.createSort(getProject(), newName);
        sort.replace(newSort);
        return this;
    }

    @Override
    public int getTextOffset() {
        KSort sort = ((KSyntax) this).getSort();
        return sort.getTextOffset();
    }

    @Override
    public int getTextLength() {
        return super.getTextLength();
    }

    @Nullable
    @Override
    public TextRange getNameRangeInElement() {
        KSort sort = ((KSyntax) this).getSort();
        int startOffsetInParent = sort.getStartOffsetInParent();
        return new TextRange(startOffsetInParent, startOffsetInParent + sort.getTextLength());
    }

    @Override
    public ItemPresentation getPresentation() {
        return new SyntaxItemPresentation(this);
    }

}
