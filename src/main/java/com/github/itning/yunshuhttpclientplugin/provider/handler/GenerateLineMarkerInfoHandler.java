package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.github.itning.yunshuhttpclientplugin.MessageBundle;
import com.github.itning.yunshuhttpclientplugin.service.HttpToolWindowProjectService;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import org.jetbrains.annotations.NotNull;

/**
 * 生成LineMarkerInfo
 *
 * @author itning
 * @see LineMarkerInfo
 * @since 2022/6/14 21:29
 */
public class GenerateLineMarkerInfoHandler implements PsiElementHandler {
    @Override
    public boolean canHandler(@NotNull PsiElement psiElement, @NotNull Result result) {
        return psiElement instanceof PsiIdentifier && result.getUrlPath() != null && !result.getUrlPath().isBlank();
    }

    @Override
    public boolean handler(@NotNull PsiElement psiElement, @NotNull Result result) {
        result.setLineMarkerInfo(initLineMarkerInfo(result.getUrlPath(), (PsiIdentifier) psiElement));
        return true;
    }

    @NotNull
    private LineMarkerInfo<PsiElement> initLineMarkerInfo(String path, PsiIdentifier psiIdentifier) {
        LineMarkerInfo<PsiElement> lineMarkerInfo = new LineMarkerInfo<>(
                psiIdentifier,
                psiIdentifier.getTextRange(),
                AllIcons.Actions.Find,
                (elt) -> MessageBundle.message("requestMappingLineMarkerProviderTooltip"),
                (e, elt) -> {
                    HttpToolWindowProjectService.getInstance(psiIdentifier.getProject()).setUrl(path);
                    ToolWindow toolWindow = ToolWindowManager.getInstance(elt.getProject()).getToolWindow("Http Client");
                    if (null != toolWindow) {
                        toolWindow.show();
                    }
                },
                GutterIconRenderer.Alignment.CENTER,
                MessageBundle.messagePointer("requestMappingLineMarkerProviderAccessibleName"));
        return lineMarkerInfo;
    }
}
