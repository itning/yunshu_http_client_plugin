package com.github.itning.yunshuhttpclientplugin.provider.handler;

import com.github.itning.yunshuhttpclientplugin.engine.AbstractHandlerEngine;
import com.github.itning.yunshuhttpclientplugin.engine.Handler;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理引擎
 *
 * @author itning
 * @since 2022/6/14 21:13
 */
public final class HandlerEngine extends AbstractHandlerEngine<PsiElement, Result> {

    public static final HandlerEngine INSTANCE = new HandlerEngine();

    private final List<Handler<PsiElement, Result>> handlerList;

    private HandlerEngine() {
        handlerList = new ArrayList<>(3);
        handlerList.add(new PsiClassHandler());
        handlerList.add(new PsiMethodHandler());
        handlerList.add(new GenerateLineMarkerInfoHandler());
    }

    @Override
    protected @NotNull Result initResult() {
        return new Result();
    }

    @Override
    protected List<Handler<PsiElement, Result>> initHandlers() {
        return handlerList;
    }
}
