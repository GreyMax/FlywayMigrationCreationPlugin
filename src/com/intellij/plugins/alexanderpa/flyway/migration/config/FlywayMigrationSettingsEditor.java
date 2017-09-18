package com.intellij.plugins.alexanderpa.flyway.migration.config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FlywayMigrationSettingsEditor extends SettingsEditor<FlywayMigrationConfig> {

    @Override
    protected void resetEditorFrom(@NotNull FlywayMigrationConfig flywayMigrationConfig) {
        FlywayConfigPanel configPanel = (FlywayConfigPanel) getComponent();
        configPanel.setPattern(flywayMigrationConfig.getPattern());
    }

    @Override
    protected void applyEditorTo(@NotNull FlywayMigrationConfig flywayMigrationConfig) throws ConfigurationException {
        FlywayConfigPanel configPanel = (FlywayConfigPanel) getComponent();
        flywayMigrationConfig.setPattern(configPanel.getPattern());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return new FlywayConfigPanel();
    }
}
