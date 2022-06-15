package com.github.itning.yunshuhttpclientplugin.toolwindow;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author itning
 * @since 2022/6/15 11:05
 */
public class HttpToolWindow {
    @Getter
    private JPanel rootPanel;
    private JComboBox<String> methodComboBox;
    private JTextField urlTextField;
    private JButton sendButton;
    private JTabbedPane tabbedPane;
    private JPanel paramPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel responsePanel;
    private JTextPane responsePane;

    public void setUrl(@Nullable String url) {
        urlTextField.setText(url);
    }

    public void setResponseText(@Nullable String text) {
        responsePane.setText(text);
    }

    public void openPanel(@NotNull TabbedPane pane) {
        tabbedPane.setSelectedIndex(pane.getIndex());
    }

    public enum TabbedPane {
        PARAMS(0),
        HEADER(1),
        BODY(2),
        RESPONSE(3),
        ;
        @Getter
        private final int index;

        TabbedPane(int index) {
            this.index = index;
        }
    }
}
