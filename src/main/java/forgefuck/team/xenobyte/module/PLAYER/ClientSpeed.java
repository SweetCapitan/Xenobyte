package forgefuck.team.xenobyte.module.PLAYER;

import forgefuck.team.xenobyte.api.config.Cfg;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.gui.click.elements.Panel;
import forgefuck.team.xenobyte.gui.click.elements.ScrollSlider;
import forgefuck.team.xenobyte.utils.Reflections;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

public class ClientSpeed extends CheatModule {
    
    @Cfg private int tickRate; 
    private Timer vanilaTimer;
    
    @Override public void onPreInit() {
        tickRate = 1;
    }
    
    @Override public void onPostInit() {
        vanilaTimer = Reflections.getPrivateValue(Minecraft.class, utils.mc(), 16);
    }
    
    @Override public void onDisabled() {
        vanilaTimer.timerSpeed = 1;
    }
    
    @Override public void onTick(boolean inGame) {
        if (inGame) {
            vanilaTimer.timerSpeed = tickRate;
        }    
    }
    
    @Override public String moduleDesc() {
        return "Изменение скорости клиентских тиков";
    }
    
    @Override public Panel settingPanel() {
        return new Panel(
            new ScrollSlider("TickRate", tickRate, 20) {
                @Override public void onScroll(int dir, boolean withShift) {
                    tickRate = processSlider(dir, withShift);
                }
            }
        );
    }

}