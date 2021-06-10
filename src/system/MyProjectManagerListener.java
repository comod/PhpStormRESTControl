package system;

import com.intellij.ide.RecentProjectsManagerBase;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

public class MyProjectManagerListener implements ProjectManagerListener {

    public void projectOpened(Project project) {

        //System.out.println("projectOpened" + project.toString());

        StatusBar statusBar = new StatusBar(project);
        statusBar.setMessage("Port: " + system.Server.getCurrentPort());

    }

    //    public void projectClosed(Project project) {
    //        this.server.closeServer();
    //    }

}