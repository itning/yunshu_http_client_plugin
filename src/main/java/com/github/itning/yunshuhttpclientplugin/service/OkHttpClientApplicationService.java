package com.github.itning.yunshuhttpclientplugin.service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * OkHttpClient应用级服务
 *
 * @author itning
 * @since 2022/6/14 20:16
 */
public class OkHttpClientApplicationService {
    private static final Logger logger = Logger.getInstance(OkHttpClientApplicationService.class);

    public static OkHttpClientApplicationService getInstance() {
        return ApplicationManager.getApplication().getService(OkHttpClientApplicationService.class);
    }

    private final OkHttpClient client;

    public OkHttpClientApplicationService() {
        client = new OkHttpClient.Builder()
                .build();
    }

    public void asyncCall(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }
}
