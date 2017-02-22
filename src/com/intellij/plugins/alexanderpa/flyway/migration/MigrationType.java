package com.intellij.plugins.alexanderpa.flyway.migration;

import org.apache.commons.lang.StringUtils;

import java.util.function.Supplier;

public enum MigrationType {
    VERSIONED("V", ".sql", MigrationTimestampGenerator::generate),
    REPEATABLE("R", ".sql", () -> StringUtils.EMPTY);

    private static final String SEPARATOR = "__";

    private String prefix;
    private String suffix;
    private Supplier<String> versionGenerator;

    MigrationType(String prefix, String suffix, Supplier<String> versionGenerator) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.versionGenerator = versionGenerator;
    }

    public String buildMigrationFileName(String migrationName) {
        return new StringBuilder()
                .append(prefix)
                .append(versionGenerator.get())
                .append(SEPARATOR)
                .append(migrationName)
                .append(suffix)
                .toString();
    }
}
