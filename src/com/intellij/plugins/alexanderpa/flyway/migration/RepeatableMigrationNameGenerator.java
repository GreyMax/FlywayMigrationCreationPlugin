package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.project.Project;

public class RepeatableMigrationNameGenerator extends MigrationNameGenerator {

    public RepeatableMigrationNameGenerator(Project project) {
        super(project);
    }

    @Override
    public String getPrefix() {
        return "R";
    }
}
