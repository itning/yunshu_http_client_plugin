package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.github.itning.yunshuhttpclientplugin.constant.Annotation;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 类上处理器
 *
 * @author itning
 * @since 2022/6/14 21:12
 */
public class PsiMethodHandler extends AbstractHandler {
    @Override
    public boolean canHandler(@NotNull PsiElement psiElement, @NotNull Result result) {
        return psiElement.getParent() instanceof PsiMethod;
    }

    @Override
    public boolean handler(@NotNull PsiElement psiElement, @NotNull Result result) {
        PsiModifierList itemMethodModifier = ((PsiMethod) psiElement.getParent()).getModifierList();
        PsiAnnotation psiAnnotation = getSupportMethodAnnotation(itemMethodModifier);
        if (null == psiAnnotation) {
            return false;
        }
        List<String> value = getAttributeValueByNameFromAnnotation("value", psiAnnotation);
        if (value.isEmpty() && (result.getUrlPrefix() == null || result.getUrlPrefix().isBlank())) {
            return false;
        }
        result.setUrlPrefix(result.getUrlPrefix() == null ? "" : result.getUrlPrefix());
        String path = result.getUrlPrefix() + (value.isEmpty() ? "" : value.get(0));
        result.setUrlPath(path);
        return true;
    }

    private PsiAnnotation getSupportMethodAnnotation(PsiModifierList itemMethodModifier) {
        PsiAnnotation getMapping = Annotation.GetMapping.findAnnotation(itemMethodModifier);
        if (null != getMapping) {
            return getMapping;
        }
        PsiAnnotation postMapping = Annotation.PostMapping.findAnnotation(itemMethodModifier);
        if (null != postMapping) {
            return postMapping;
        }
        PsiAnnotation requestMapping = Annotation.RequestMapping.findAnnotation(itemMethodModifier);
        if (null != requestMapping) {
            return requestMapping;
        }

        return null;
    }
}
