package com.intellij.plugins.alexanderpa.flyway.migration.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.plugins.alexanderpa.flyway.migration.MigrationService;
import com.intellij.plugins.alexanderpa.flyway.migration.MigrationType;

public class NewRepeatableMigration extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        MigrationService.getInstance().showCreateMigrationDialog(e, MigrationType.REPEATABLE);
    }
}
