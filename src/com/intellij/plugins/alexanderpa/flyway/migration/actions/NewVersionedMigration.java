package com.intellij.plugins.alexanderpa.flyway.migration.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.plugins.alexanderpa.flyway.migration.MigrationService;
import com.intellij.plugins.alexanderpa.flyway.migration.MigrationType;

public class NewVersionedMigration extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        assert project != null;

        MigrationService.getInstance(project).showCreateMigrationDialog(e, MigrationType.VERSIONED);
    }
}
