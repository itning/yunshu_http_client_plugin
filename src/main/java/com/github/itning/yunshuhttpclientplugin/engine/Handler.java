package com.github.itning.yunshuhttpclientplugin.engine;

import org.jetbrains.annotations.NotNull;

/**
 * 处理器
 *
 * @author itning
 * @since 2022/6/14 21:38
 */
public interface Handler<HANDLER_ITEM, RESULT> {
    /**
     * 能否处理
     *
     * @param handlerItem 处理项
     * @param result      处理结果
     * @return 能处理返回<code>true</code>
     */
    boolean canHandler(@NotNull HANDLER_ITEM handlerItem, @NotNull RESULT result);

    /**
     * 处理
     *
     * @param handlerItem 处理项
     * @param result      处理结果
     * @return 处理成功返回<code>true</code>
     */
    boolean handler(@NotNull HANDLER_ITEM handlerItem, @NotNull RESULT result);
}
