package system;

import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

import javax.swing.*;

public class StatusBar {

    private Project project;
    private JLabel label;

    public StatusBar(Project project) {
        this.label = new JLabel("...");
        this.project = project;
        addCustomIndicationComponent();
    }

    private void addCustomIndicationComponent() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WindowManager.getInstance().getStatusBar(StatusBar.this.project).addCustomIndicationComponent(StatusBar.this.label);
            }
        });

    }

    public void setMessage(String message) {
        this.label.setText(message);
    }

}