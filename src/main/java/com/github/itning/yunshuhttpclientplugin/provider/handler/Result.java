package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.github.itning.yunshuhttpclientplugin.engine.AbstractResult;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.psi.PsiElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

/**
 * 结果信息
 *
 * @author itning
 * @since 2022/6/14 21:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Result extends AbstractResult {

    @Nullable
    private String urlPrefix;

    @Nullable
    private String urlPath;

    @Nullable
    private LineMarkerInfo<PsiElement> lineMarkerInfo;
}
