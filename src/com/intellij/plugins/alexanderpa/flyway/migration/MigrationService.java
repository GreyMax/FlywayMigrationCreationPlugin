package com.intellij.plugins.alexanderpa.flyway.migration;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.plugins.alexanderpa.flyway.migration.MigrationType.VERSIONED;
import static org.apache.commons.lang.StringUtils.EMPTY;

public class MigrationService {

    private static final String MIGRATION_SEPARATOR = "__";
    private static final String MIGRATION_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(MIGRATION_TIMESTAMP_FORMAT);
    private static final MigrationService instance = new MigrationService();

    public static MigrationService getInstance() {
        return instance;
    }

    public void showCreateMigrationDialog(AnActionEvent event, MigrationType migrationType) {
        String migrationName = Messages.showInputDialog("Enter migration description", "New flyway migration", null, null, new InputValidator() {
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
        PsiElement psiElement = DataKeys.PSI_ELEMENT.getData(event.getDataContext());
        if (null != psiElement) {
            PsiDirectory directory = (PsiDirectory) (psiElement instanceof PsiDirectory ?  psiElement : psiElement.getParent());
            String fileName = generateFileName(migrationType, migrationName);

            Application app = ApplicationManager.getApplication();
            PsiFile psiFile = app.runWriteAction((Computable<PsiFile>) () -> directory.createFile(fileName));
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            fileEditorManager.openFile(psiFile.getVirtualFile(), true);
        }
    }

    private String generateFileName(MigrationType migrationType, String migrationName) {
        return new StringBuilder()
                .append(getMigrationFilePrefix(migrationType))
                .append(getMigrationVersion(migrationType))
                .append(MIGRATION_SEPARATOR)
                .append(fixMigrationName(migrationName))
                .append(getMigrationFileSuffix())
                .toString();
    }

    private String fixMigrationName(String migrationName) {
        return migrationName.trim().replaceAll("\\s+", "_");
    }

    private String getMigrationFilePrefix(MigrationType migrationType) {
        switch (migrationType) {
            case VERSIONED: return "V";
            case REPEATABLE: return "R";
            default: throw new RuntimeException("Not supported migration type");
        }
    }

    private String getMigrationVersion(MigrationType migrationType) {
        return migrationType.equals(VERSIONED) ? sdf.format(new Date()) : EMPTY;
    }

    private String getMigrationFileSuffix() {
        return ".sql";
    }
}
