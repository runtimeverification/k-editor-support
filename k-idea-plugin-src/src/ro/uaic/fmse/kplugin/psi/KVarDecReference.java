// Copyright (c) 2013-2014 K Team. All Rights Reserved.
package ro.uaic.fmse.kplugin.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Bogdanas
 * Created on 12/11/13.
 * Altouugh this class is Poly, actually this reference resolves to just one target.
 */
public class KVarDecReference extends PsiReferenceBase.Poly<IKVarDecBase> {

    private String name;

    public KVarDecReference(@NotNull IKVarDecBase element) {
        super(element, new TextRange(0, element.getTextLength()), true);
        name = element.getText();
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        PsiNamedElement varDecRes =
                KPsiUtil.findGeneralizedVarDecInSameRule(((PsiReference) this).getElement(), name);
        return varDecRes != null ? new ResolveResult[]{new PsiElementResolveResult(varDecRes)} :
                new ResolveResult[0];
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        return getElement().setName(newElementName);
    }
}
