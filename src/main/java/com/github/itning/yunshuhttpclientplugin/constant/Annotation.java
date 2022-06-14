package com.github.itning.yunshuhttpclientplugin.constant;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationOwner;
import com.intellij.psi.PsiModifierList;
import lombok.Getter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 注解常量
 *
 * @author itning
 * @since 2022/6/14 20:35
 */
public enum Annotation {
    /**
     * <code>@GetMapping</code> Annotation
     */
    GetMapping("org.springframework.web.bind.annotation.GetMapping"),
    /**
     * <code>@PostMapping</code> Annotation
     */
    PostMapping("org.springframework.web.bind.annotation.PostMapping"),
    /**
     * <code>@RequestMapping</code> Annotation
     */
    RequestMapping("org.springframework.web.bind.annotation.RequestMapping"),
    /**
     * <code>@Controller</code> Annotation
     */
    Controller("org.springframework.stereotype.Controller"),
    /**
     * <code>@RestController</code> Annotation
     */
    RestController("org.springframework.web.bind.annotation.RestController"),
    ;

    /**
     * 类全限定名
     */
    @Getter
    @NotNull
    @NonNls
    private final String qualifiedName;

    Annotation(@NotNull String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    /**
     * 是否有这个注解
     *
     * @param modifierList {@link PsiModifierList}
     * @return 如果有这个注解则返回<code>true</code>
     * @see PsiAnnotationOwner#hasAnnotation(String)
     */
    public boolean hasAnnotation(@NotNull PsiModifierList modifierList) {
        return modifierList.hasAnnotation(this.qualifiedName);
    }

    /**
     * 查找注解
     *
     * @param modifierList {@link PsiModifierList}
     * @return 查找到的注解，可能为空
     * @see PsiAnnotationOwner#findAnnotation(String)
     */
    @Nullable
    public PsiAnnotation findAnnotation(@NotNull PsiModifierList modifierList) {
        return modifierList.findAnnotation(this.qualifiedName);
    }
}
