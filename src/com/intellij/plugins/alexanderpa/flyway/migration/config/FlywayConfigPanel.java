package com.intellij.plugins.alexanderpa.flyway.migration.config;

import javax.swing.*;
import java.awt.*;

public class FlywayConfigPanel extends JPanel {
    private static final Dimension FIELD_SIZE = new Dimension(200, 25);

    private JTextField patternField;

    public FlywayConfigPanel() {
        super(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("Version timestamp pattern");
        row1.add(label);
        patternField = new JTextField();
        patternField.setMinimumSize(FIELD_SIZE);
        patternField.setPreferredSize(FIELD_SIZE); // TODO: resize
        row1.add(patternField);

        add(row1, BorderLayout.PAGE_START);
        add(row2, BorderLayout.LINE_START);
    }

    public void setPattern(String pattern) {
        patternField.setText(pattern);
    }

    public String getPattern() {
        return patternField.getText();
    }
}
