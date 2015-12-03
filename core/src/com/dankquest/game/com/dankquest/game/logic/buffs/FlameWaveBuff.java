package com.dankquest.game.com.dankquest.game.logic.buffs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;

/**
 * Created by KTamo_000 on 2015-12-03.
 */
public class FlameWaveBuff extends DOTBuff {
    public FlameWaveBuff() {
        basePixmap = new Pixmap(Gdx.files.internal("buffs/exhausted.png"));
        buffDuration = 3;
        name = "Burning";
    }
    public void useBuff(Hero hero) {

    }
    public void applyDOT(Hero hero){
        hero.recieveDamage(35,false);
    }
}
