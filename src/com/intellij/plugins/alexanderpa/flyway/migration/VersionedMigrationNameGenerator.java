package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.project.Project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VersionedMigrationNameGenerator extends MigrationNameGenerator {

    public VersionedMigrationNameGenerator(Project project) {
        super(project);
    }

    @Override
    public String getPrefix() {
        return "V" + getTimeStamp();
    }

    private String getTimeStamp() {
        String pattern = flywayMigrationConfig.getPattern();
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
