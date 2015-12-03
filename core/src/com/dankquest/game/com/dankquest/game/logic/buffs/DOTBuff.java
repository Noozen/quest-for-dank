package com.dankquest.game.com.dankquest.game.logic.buffs;

import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by KTamo_000 on 2015-12-03.
 */
abstract public class DOTBuff extends Buff{
    @Override
    public void useBuff(Hero hero) {

    }
    abstract public void applyDOT(Hero hero);
}
