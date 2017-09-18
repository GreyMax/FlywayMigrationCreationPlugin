package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.project.Project;
import com.intellij.plugins.alexanderpa.flyway.migration.config.FlywayMigrationConfig;

public abstract class MigrationNameGenerator {
    public static final String SUFFIX = ".sql";
    public static final String NAME_SEPARATOR = "__";

    protected FlywayMigrationConfig flywayMigrationConfig;

    public MigrationNameGenerator(Project project) {
        flywayMigrationConfig = FlywayMigrationConfig.getInstance(project);
    }

    public abstract String getPrefix();

    public String getSuffix() {
        return SUFFIX;
    }

    public String getNameSeparator() {
        return NAME_SEPARATOR;
    }

    public String generate(String migrationName) {
        return new StringBuilder()
                .append(getPrefix())
                .append(getNameSeparator())
                .append(migrationName)
                .append(getSuffix())
                .toString();
    }
}
