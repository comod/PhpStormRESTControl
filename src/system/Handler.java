package system;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Handler implements HttpHandler {

    public Handler() {

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Server.Handler.setCors(httpExchange);

        URI requestURI = httpExchange.getRequestURI();
        String query = requestURI.getQuery();

        Map<String, String> queryParams = getParamMap(query);

        String file = query;
        int line = 0;

        if (queryParams.containsKey("file")) {
            file = queryParams.get("file");
            if (queryParams.containsKey("line")) {
                line = Integer.parseInt(queryParams.get("line")) - 1;
            }
        }

        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        for (Project project : projects) {
            Editor editor = new Editor(project);
            editor.jumpToLine(file, line);
        }

        Server.Handler.response(httpExchange);

    }

    private static Map<String, String> getParamMap(final String query) {

        try {
            if (query == null || query.isEmpty()) {
                return Collections.emptyMap();
            }

            return Stream.of(query.split("&"))
                    .filter(s -> !s.isEmpty())
                    .map(kv -> Arrays.copyOf(kv.split("=", 2), 2))
                    .collect(Collectors.toMap(x -> decode(x[0]), x -> decode(x[1])));
        } catch (Exception e) {
            return Collections.emptyMap();
        }

    }

    private static String decode(final String encoded) {

        return Optional.ofNullable(encoded)
                .map(e -> URLDecoder.decode(e, StandardCharsets.UTF_8))
                .orElse(null);

    }

}
