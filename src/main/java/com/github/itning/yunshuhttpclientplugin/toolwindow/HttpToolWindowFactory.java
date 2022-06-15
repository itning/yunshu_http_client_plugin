package com.github.itning.yunshuhttpclientplugin.toolwindow;

import com.github.itning.yunshuhttpclientplugin.MessageBundle;
import com.github.itning.yunshuhttpclientplugin.service.HttpToolWindowProjectService;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Http请求工具窗口
 *
 * @author itning
 * @since 2022/6/15 11:02
 */
public class HttpToolWindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void init(@NotNull ToolWindow toolWindow) {
        toolWindow.setStripeTitle(MessageBundle.message("httpToolWindowStripeTitle"));
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel rootPanel = HttpToolWindowProjectService.getInstance(project).getRootPanel();
        Content content = ContentFactory.SERVICE.getInstance().createContent(rootPanel, "", true);
        ContentManager contentManager = toolWindow.getContentManager();
        contentManager.addContent(content);
    }
}
