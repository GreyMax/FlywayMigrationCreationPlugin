package com.intellij.plugins.alexanderpa.flyway.migration.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;

public class NewMigrationGroup extends DefaultActionGroup {

    public void update(AnActionEvent e) {
        super.update(e);
        Presentation presentation = e.getPresentation();
        Project project = e.getData(DataKeys.PROJECT);
        if (project == null) {
            //If no project defined, disable the menu item
            presentation.setEnabled(false);
            presentation.setVisible(false);
        } else {
            presentation.setVisible(true);
        }
    }
}
