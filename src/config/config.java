package config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * PersistentStateComponent keeps project config values.
 * Similar notion of 'preference' in Android
 */
@State(
    name = "PhpStormRESTControl",
    storages = {
        @Storage(
                file="PhpStormRESTControl.xml"
        )
    },
    reloadable=true
)
public class config implements PersistentStateComponent<config> {

    /**
     * HOWTO:
     * - Add to plugin.xml: <projectService serviceInterface="config.config" serviceImplementation="config.config"/>
     * - To Create a "node" just add class property: public String data = "";
     * -- Implement Getter and Setter
     * --- By using the setter the data is saved
     */

    public String data = "";

    public String dataJson = "";

    public String getDataJson() {
        return this.dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Nullable
    @Override
    public config getState() {
        return this;
    }

    @Override
    public void loadState(config config) {
        XmlSerializerUtil.copyBean(config, this);
    }

    @Nullable
    public static config getInstance(Project project) {
        config sfec = ServiceManager.getService(project, config.class);
        return sfec;
    }
}
