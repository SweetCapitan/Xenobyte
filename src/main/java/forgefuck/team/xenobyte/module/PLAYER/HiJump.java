package forgefuck.team.xenobyte.module.PLAYER;

import forgefuck.team.xenobyte.api.config.Cfg;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.gui.click.elements.Panel;
import forgefuck.team.xenobyte.gui.click.elements.ScrollSlider;
import forgefuck.team.xenobyte.utils.TickHelper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class HiJump extends CheatModule {
    
    @Cfg private int power;
    
    private void removePotion() {
        utils.player().removePotionEffect(Potion.jump.getId());
    }
    
    @Override public void onPreInit() {
        power = 5;
    }
    
    @Override public void onDisabled() {
        removePotion();
    }
    
    @Override public int tickDelay() {
        return TickHelper.oneSecond();
    }
    
    @Override public void onTick(boolean inGame) {
        if (inGame) {
            removePotion();
            utils.player().addPotionEffect(new PotionEffect(Potion.jump.getId(), Integer.MAX_VALUE, power));
        }
    }
    
    @Override public String moduleDesc() {
        return "Прыжок с заданной мощностью";
    }
    
    @Override public Panel settingPanel() {
        return new Panel(
            new ScrollSlider("Power", power, 20) {
                @Override public void onScroll(int dir, boolean withShift) {
                    power = processSlider(dir, withShift);
                }
            }
        );
    }

}
