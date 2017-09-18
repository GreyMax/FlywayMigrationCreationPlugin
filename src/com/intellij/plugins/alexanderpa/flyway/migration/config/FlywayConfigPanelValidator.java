package com.intellij.plugins.alexanderpa.flyway.migration.config;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorListener;

import java.text.SimpleDateFormat;

public class FlywayConfigPanelValidator implements SettingsEditorListener<FlywayMigrationConfig> {

    @Override
    public void stateChanged(SettingsEditor<FlywayMigrationConfig> settingsEditor) {
        FlywayConfigPanel configPanel = (FlywayConfigPanel) settingsEditor.getComponent();
        String pattern = configPanel.getPattern();
        configPanel.setValidPattern(validate(pattern));
    }

    public boolean validate(String pattern) {
        try {
            new SimpleDateFormat().applyPattern(pattern);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
