package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;

public class MigrationService {

    public static MigrationService getInstance(Project project) {
        return ServiceManager.getService(project, MigrationService.class);
    }

    public void showCreateMigrationDialog(AnActionEvent event, MigrationType migrationType) {
        String migrationName = Messages.showInputDialog("Enter migration description", "New Flyway Migration", null, null, new InputValidator() {
            @Override
            public boolean checkInput(String s) {
                return !s.trim().equals(StringUtils.EMPTY);
            }

            @Override
            public boolean canClose(String s) {
                return true;
            }
        });
        if (null != migrationName) {
            createMigration(event, migrationType, migrationName);
        }
    }

    private void createMigration(AnActionEvent event, MigrationType migrationType, String migrationName) {
        Project project = event.getProject();
        assert project != null;

        Optional<PsiDirectory> psiDirectory = getDirectory(event);
        psiDirectory.ifPresent(directory -> {
            String fileName = migrationType.buildMigrationFileName(project,
                    fixMigrationName(migrationName));

            Application app = ApplicationManager.getApplication();
            PsiFile psiFile = app.runWriteAction((Computable<PsiFile>) () -> directory.createFile(fileName));
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            fileEditorManager.openFile(psiFile.getVirtualFile(), true);
        });
    }

    private Optional<PsiDirectory> getDirectory(AnActionEvent event) {
        PsiElement psiElement = DataKeys.PSI_ELEMENT.getData(event.getDataContext());
        while (psiElement != null && !(psiElement instanceof PsiDirectory)) {
            psiElement = psiElement.getParent();
        }
        return Optional.ofNullable(psiElement)
                .map(el -> (PsiDirectory) el);
    }

    private String fixMigrationName(String migrationName) {
        return migrationName.trim().replaceAll("\\s+", "_");
    }

}
