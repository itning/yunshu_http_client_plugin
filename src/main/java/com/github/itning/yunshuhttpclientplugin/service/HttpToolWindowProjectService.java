package com.github.itning.yunshuhttpclientplugin.service;

import com.github.itning.yunshuhttpclientplugin.toolwindow.HttpToolWindow;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author itning
 * @since 2022/6/15 13:41
 */
public class HttpToolWindowProjectService {
    private final Project project;
    private final HttpToolWindow httpToolWindow;

    public static HttpToolWindowProjectService getInstance(@NotNull Project project) {
        return project.getService(HttpToolWindowProjectService.class);
    }

    public HttpToolWindowProjectService(Project project) {
        this.project = project;
        this.httpToolWindow = new HttpToolWindow();
    }

    @NotNull
    public JPanel getRootPanel() {
        return this.httpToolWindow.getRootPanel();
    }

    public void setUrl(@Nullable String url) {
        this.httpToolWindow.setUrl(url);
    }
}
