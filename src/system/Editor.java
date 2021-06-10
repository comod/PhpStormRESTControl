package system;

//import com.google.common.collect.Lists;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

public class Editor {

    private Project project;

    public Editor(Project project) {
        this.project = project;
    }

    public void jumpToLine(String path, Integer line) {

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

            } else {
                //Notification.notify("Couldnt find the file: " + path);
            }
        } catch (Exception e) {
            Notification.notify(e.getMessage());
        }

    }

}
