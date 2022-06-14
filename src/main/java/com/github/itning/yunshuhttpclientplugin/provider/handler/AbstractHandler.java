package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.intellij.lang.jvm.annotation.JvmAnnotationArrayValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttributeValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationConstantValue;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiNameValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽象处理器
 *
 * @author itning
 * @since 2022/6/14 21:18
 */
public abstract class AbstractHandler implements PsiElementHandler {
    @NotNull
    protected final List<String> getAttributeValueByNameFromAnnotation(@NotNull String attributeName, @NotNull PsiAnnotation psiAnnotation) {
        PsiNameValuePair[] attributes = psiAnnotation.getParameterList().getAttributes();
        for (PsiNameValuePair psiNameValuePair : attributes) {
            if (!attributeName.equals(psiNameValuePair.getAttributeName())) {
                continue;
            }
            JvmAnnotationAttributeValue attributeValue = psiNameValuePair.getAttributeValue();
            return getAttributeValuesFromAnnotation(attributeValue);
        }
        return Collections.emptyList();
    }

    @NotNull
    protected final List<String> getAttributeValuesFromAnnotation(@Nullable JvmAnnotationAttributeValue attributeValue) {
        if (attributeValue instanceof JvmAnnotationConstantValue) {
            JvmAnnotationConstantValue constantValue = (JvmAnnotationConstantValue) attributeValue;
            if (constantValue.getConstantValue() == null) {
                return Collections.emptyList();
            } else {
                String value = constantValue.getConstantValue().toString();
                if (value.isBlank()) {
                    return Collections.emptyList();
                }
                return Collections.singletonList(value);
            }
        } else if (attributeValue instanceof JvmAnnotationArrayValue) {
            JvmAnnotationArrayValue arrayValue = (JvmAnnotationArrayValue) attributeValue;
            List<JvmAnnotationAttributeValue> values = arrayValue.getValues();
            List<String> result = new ArrayList<>(values.size());
            for (JvmAnnotationAttributeValue annotationAttributeValue : values) {
                result.addAll(getAttributeValuesFromAnnotation(annotationAttributeValue));
            }
            return result;
        } else {
            System.out.println("UnknownType: " + attributeValue);
            return Collections.emptyList();
        }
    }
}
