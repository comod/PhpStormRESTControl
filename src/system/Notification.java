package system;

import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.messages.Topic;

import javax.naming.Context;
import javax.swing.*;


import com.intellij.util.messages.Topic;

import javax.naming.Context;
import java.awt.*;

public class Notification {

    /**
     * Notify - IDE Notifications
     *
     * @param message
     */
    public static void notify(String message) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Notifications.Bus.notify(
                    new com.intellij.notification.Notification(
                        "PhpStormRESTControl",
                        "PhpStormRESTControl",
                        message,
                        NotificationType.INFORMATION
                    )
                );
            }
        });

    }

}