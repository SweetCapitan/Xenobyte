package forgefuck.team.xenobyte.module.MODS;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import forgefuck.team.xenobyte.api.config.Cfg;
import forgefuck.team.xenobyte.api.gui.InputType;
import forgefuck.team.xenobyte.api.module.PerformMode;
import forgefuck.team.xenobyte.api.module.PerformSource;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.gui.click.elements.Button;
import forgefuck.team.xenobyte.gui.click.elements.Panel;
import forgefuck.team.xenobyte.gui.swing.UserInput;
import forgefuck.team.xenobyte.utils.Rand;

public class AdvertHack extends CheatModule {
    
    @Cfg private List<String> urls;
    
    public AdvertHack() {
        (urls = new ArrayList<String>()).add("");
    }
    
    @Override public PerformMode performMode() {
        return PerformMode.SINGLE;
    }
    
    @Override public void onPerform(PerformSource src) {
        String url = urls.get(0);
        if (!url.isEmpty()) {
            for (int id = 0; id <= 100; id++) {
                utils.sendPacket("malisisadvert", (byte) 7, id, Rand.str(), url);
            }
        }
    }
    
    @Override public boolean isWorking() {
        return Loader.isModLoaded("malisisadvert");
    }
    
    @Override public String moduleDesc() {
        return "Замена всех рекламных картинок в MalisisAdvert на картинку из ссылки";
    }
    
    @Override public Panel settingPanel() {
        return new Panel(
            new Button("AdvertUrl") {
                @Override public void onLeftClick() {
                    new UserInput("Advert url", urls, InputType.SINGLE_STRING).showFrame();
                }
            }
        );
    }

}
