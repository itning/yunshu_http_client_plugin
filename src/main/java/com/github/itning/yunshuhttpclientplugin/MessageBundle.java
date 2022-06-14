package com.github.itning.yunshuhttpclientplugin;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

/**
 * 消息本地化
 *
 * @author itning
 * @since 2022/6/14 20:06
 */
public final class MessageBundle extends DynamicBundle {
    @NonNls
    private static final String BUNDLE = "messages.MessageBundle";

    @NotNull
    public static final MessageBundle INSTANCE = new MessageBundle();

    public MessageBundle() {
        super(BUNDLE);
    }

    @NotNull
    @Nls
    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object @NotNull ... params) {
        return INSTANCE.getMessage(key, params);
    }

    @NotNull
    public static Supplier<@Nls String> messagePointer(@PropertyKey(resourceBundle = BUNDLE) String key, Object @NotNull ... params) {
        return INSTANCE.getLazyMessage(key, params);
    }
}
