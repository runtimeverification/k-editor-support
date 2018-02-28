// Copyright (c) 2013-2014 K Team. All Rights Reserved.
package ro.uaic.fmse.kplugin.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
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
public class PsiKSyntaxRhsRegularBase extends StubBasedPsiElementBase<KRegularProductionStub>
        implements KRegularProduction {

    public PsiKSyntaxRhsRegularBase(@NotNull ASTNode node) {
        super(node);
    }

    public PsiKSyntaxRhsRegularBase(final KRegularProductionStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    @Nullable
    @Override
    public String getName() {
        final KRegularProductionStub stub = getStub();
        if (stub != null) {
            return stub.getName();
        }

        PsiElement firstChild = getFirstChild();
        if (KTypes.STRING.equals(firstChild.getNode().getElementType())) {
            String text = firstChild.getText();
            return text.substring(1, text.length() - 1);
        } else {
            return null;
        }
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
        PsiElement firstChild = getFirstChild();
        if (KTypes.STRING.equals(firstChild.getNode().getElementType())) {
            PsiElement newString = KElementFactory.createString(getProject(), newName);
            firstChild.replace(newString);
            return this;
        } else {
            throw new IncorrectOperationException();
        }
    }

    @Override
    public int getTextOffset() {
        PsiElement firstChild = getFirstChild();
        if (KTypes.STRING.equals(firstChild.getNode().getElementType())) {
            return firstChild.getTextOffset() + 1;
        } else {
            return super.getTextOffset();
        }
    }

    @Override
    public PsiReference getReference() {
        return getName() != null ? new KRegularProductionReference(this) : null;
    }

    @Nullable
    @Override
    public TextRange getNameRangeInElement() {
        PsiElement firstChild = getFirstChild();
        if (KTypes.STRING.equals(firstChild.getNode().getElementType())) {
            return new TextRange(firstChild.getStartOffsetInParent() + 1, firstChild.getTextLength() - 1);
        } else {
            return null;
        }
    }

    @Override
    public ItemPresentation getPresentation() {
        return new SyntaxRhsItemPresentation(this);
    }
}
