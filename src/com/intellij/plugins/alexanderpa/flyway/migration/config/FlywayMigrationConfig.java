package com.intellij.plugins.alexanderpa.flyway.migration.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
        name = "FlywayMigrationConfig",
        storages = {
                @Storage("flyway.migration.xml")
        }
)
public class FlywayMigrationConfig implements PersistentStateComponent<FlywayMigrationConfig> {
    private String pattern = "yyyyMMddHHmmss";

    @Nullable
    @Override
    public FlywayMigrationConfig getState() {
        return this;
    }

    @Override
    public void loadState(FlywayMigrationConfig config) {
        XmlSerializerUtil.copyBean(config, this);
    }

    @Nullable
    public static FlywayMigrationConfig getInstance(Project project) {
        return ServiceManager.getService(project, FlywayMigrationConfig.class);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
