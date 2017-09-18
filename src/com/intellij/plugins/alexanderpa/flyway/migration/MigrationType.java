package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.project.Project;

import java.util.function.Function;

public enum MigrationType {
    VERSIONED(VersionedMigrationNameGenerator::new),
    REPEATABLE(RepeatableMigrationNameGenerator::new);

    private Function<Project, MigrationNameGenerator> versionGeneratorFunction;

    MigrationType(Function<Project, MigrationNameGenerator> versionGenerator) {
        this.versionGeneratorFunction = versionGenerator;
    }

    public String buildMigrationFileName(Project project, String migrationName) {
        MigrationNameGenerator nameGenerator = versionGeneratorFunction.apply(project);
        return nameGenerator.generate(migrationName);
    }
}
