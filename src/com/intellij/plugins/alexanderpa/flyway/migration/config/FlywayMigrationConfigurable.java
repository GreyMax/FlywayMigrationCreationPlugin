package com.intellij.plugins.alexanderpa.flyway.migration.config;

import com.intellij.openapi.options.SettingsEditorConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

public class FlywayMigrationConfigurable extends SettingsEditorConfigurable<FlywayMigrationConfig> {

    public FlywayMigrationConfigurable(Project project) {
        super(new FlywayMigrationSettingsEditor(), FlywayMigrationConfig.getInstance(project));
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Flyway Migration";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }
}
