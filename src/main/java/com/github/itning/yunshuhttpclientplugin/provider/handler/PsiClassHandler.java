package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.github.itning.yunshuhttpclientplugin.constant.Annotation;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifierList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 类上处理器
 *
 * @author itning
 * @since 2022/6/14 21:12
 */
public class PsiClassHandler extends AbstractHandler {
    @Override
    public boolean canHandler(@NotNull PsiElement psiElement, @NotNull Result result) {
        return null != psiElement.getParent() && psiElement.getParent().getParent() instanceof PsiClass;
    }

    @Override
    public boolean handler(@NotNull PsiElement psiElement, @NotNull Result result) {
        PsiClass psiClass = (PsiClass) psiElement.getParent().getParent();
        PsiModifierList modifierList = psiClass.getModifierList();
        if (null != modifierList) {
            // 必须是一个Controller
            if (!Annotation.RestController.hasAnnotation(modifierList) && !Annotation.Controller.hasAnnotation(modifierList)) {
                return false;
            }
            // 路径前缀
            PsiAnnotation requestMappingAnnotation = Annotation.RequestMapping.findAnnotation(modifierList);
            if (null != requestMappingAnnotation) {
                List<String> value = getAttributeValueByNameFromAnnotation("value", requestMappingAnnotation);
                String urlPrefix = value.isEmpty() ? "" : value.get(0);
                result.setUrlPrefix(urlPrefix);
            }
        }
        return true;
    }
}
