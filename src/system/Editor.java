package system;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class Editor {

    private Project project;

    public Editor(Project project) {
        this.project = project;
    }

    public void jumpToLine(@NotNull String path, Integer line) {

        try {

            VirtualFile file = this.project.getBaseDir().findFileByRelativePath(path);
            if (file != null && file.exists()) {

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        ApplicationManager.getApplication().runReadAction(new Runnable() {
                            public void run() {
                                new OpenFileDescriptor(Editor.this.project, file, line, 0, false).navigate(true);
                            }
                        });
                    }
                });

            }
        } catch (Exception e) {
            Notification.notify(e.getMessage());
        }

    }

}
