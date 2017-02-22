package com.intellij.plugins.alexanderpa.flyway.migration;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MigrationTimestampGenerator {

    private static final String MIGRATION_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(MIGRATION_TIMESTAMP_FORMAT);

    @NotNull
    public static String generate() {
        return sdf.format(new Date());
    }
}
