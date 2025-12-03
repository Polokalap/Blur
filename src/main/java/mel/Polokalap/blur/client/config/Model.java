package mel.Polokalap.blur.client.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = "blur")
@Config(name = "blur-main-config", wrapperName = "MainConfig")
public class Model {

    @SectionHeader("MotionBlurMain")

    public boolean ENABLE = true;

    @RangeConstraint(min = 1, max = 100, decimalPlaces = 0)
    public int MOTIONBLUR_AMOUNT = 25;

    @SectionHeader("MotionBlurSettings")

    public boolean SMART_MOTIONBLUR = true;

    @RangeConstraint(min = 1, max = 1000, decimalPlaces = 0)
    public int DISABLE_MOTIONBLUR_LIMIT = 60;

}
