package system;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.WindowManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class Handler implements HttpHandler {

    public Handler() {

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Server.Handler.setCors(httpExchange);

        URI requestURI = httpExchange.getRequestURI();
        String query = requestURI.getQuery();

        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        for (Project project : projects) {
            Editor editor = new Editor(project);
            editor.jumpToLine(query, 0);
        }

        Server.Handler.response(httpExchange);

        return;

    }
}
