package com.dankquest.game.com.dankquest.game.logic.buffs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by Miko on 2015-09-09.
 */
public class ExhaustedBuff extends Buff{

    public ExhaustedBuff() {
        basePixmap = new Pixmap(Gdx.files.internal("buffs/exhausted.png"));
        buffDuration = 1;
        name = "Exhausted";
    }
    public void useBuff(Hero hero) {
        hero.damageMultiplier *= 0.8;
    }
}
