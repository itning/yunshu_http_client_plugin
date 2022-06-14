package com.github.itning.yunshuhttpclientplugin.engine;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 处理器引擎
 *
 * @author itning
 * @since 2022/6/14 22:09
 */
public abstract class AbstractHandlerEngine<HANDLER_ITEM, RESULT extends AbstractResult> {
    /**
     * 初始化结果实例
     *
     * @return 结果实例
     */
    @NotNull
    protected abstract RESULT initResult();

    /**
     * 初始化处理器
     *
     * @return 处理器集合
     */
    protected abstract List<Handler<HANDLER_ITEM, RESULT>> initHandlers();

    @NotNull
    public final RESULT handle(@NotNull HANDLER_ITEM handlerItem) {
        RESULT result = initResult();
        for (Handler<HANDLER_ITEM, RESULT> handler : initHandlers()) {
            if (handler.canHandler(handlerItem, result)) {
                if (!handler.handler(handlerItem, result)) {
                    result.setSuccess(false);
                    return result;
                }
            }
        }
        result.setSuccess(true);
        return result;
    }
}
