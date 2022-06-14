package com.github.itning.yunshuhttpclientplugin.engine;

import lombok.Data;

/**
 * 结果信息
 * @author itning
 * @since 2022/6/14 21:44
 */
@Data
public abstract class AbstractResult {
    /**
     * 处理是否全部成功
     */
    private boolean success;
}
