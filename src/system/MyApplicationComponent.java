package system;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.sun.net.httpserver.HttpHandler;

public class MyApplicationComponent implements ApplicationComponent {

    private MyApplicationComponent() {

        //Notification.notify("Register MyProjectManagerListener");
        MyProjectManagerListener myProjectManagerListener = new MyProjectManagerListener();
        ProjectManager.getInstance().addProjectManagerListener(myProjectManagerListener);

        HttpHandler handler = new Handler();
        new Server(handler);

    }

}