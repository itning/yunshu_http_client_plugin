package com.github.itning.yunshuhttpclientplugin.provider;

import com.github.itning.yunshuhttpclientplugin.provider.handler.HandlerEngine;
import com.github.itning.yunshuhttpclientplugin.provider.handler.Result;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 标记可以发起请求的方法
 *
 * @author itning
 * @since 2022/6/14 20:30
 */
public class RequestMappingLineMarkerProvider implements LineMarkerProvider {

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<? extends PsiElement> elements, @NotNull Collection<? super LineMarkerInfo<?>> result) {
        for (PsiElement element : elements) {
            collectNavigationMarkers(element, result);
        }
    }

    private void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super LineMarkerInfo<?>> result) {
        PsiElement parent;
        if (element instanceof PsiIdentifier && (parent = element.getParent()) instanceof PsiMethod && ((PsiMethod) parent).getNameIdentifier() == element) {
            Result re = HandlerEngine.INSTANCE.handle(element);
            if (!re.isSuccess()) {
                return;
            }
            LineMarkerInfo<PsiElement> lineMarkerInfo = re.getLineMarkerInfo();
            if (null != lineMarkerInfo) {
                result.add(lineMarkerInfo);
            }
        }
    }
}
