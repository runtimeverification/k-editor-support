// Copyright (c) 2013-2014 K Team. All Rights Reserved.
package ro.uaic.fmse.kplugin.psi;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Bogdanas
 * Created on 12/14/13.
 */
public interface IKIdExprBase extends PsiNamedElement {
    @NotNull
    PsiReference getReference();
}
