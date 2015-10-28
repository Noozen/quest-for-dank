package com.dankquest.game.com.dankquest.game.logic.buffs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by KTamo_000 on 2015-10-13.
 */
public class IceWallBuff extends Buff{
    public IceWallBuff() {
        basePixmap = new Pixmap(Gdx.files.internal("buffs/exhausted.png"));
        buffDuration = 5;
        name = "Shield";
    }
    public void useBuff(Hero hero) {
        hero.shield += 20;
    }
}
