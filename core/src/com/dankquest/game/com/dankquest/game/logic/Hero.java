package com.dankquest.game.com.dankquest.game.logic;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.actors.HeroActor;
import com.dankquest.game.com.dankquest.game.logic.skill.Skill;

public class Hero {

    public String name;
    public HeroClass heroClass;
    public Pixmap texture;
    
    public double healthTotal;
    public double healthCurrent;
    public double attackDamage;
    public double initiative;

    public Skill skill1;
    public Skill skill2;
    public Skill skill3;
    public Skill skill4;
    public HeroActor heroActor;

    public Skill getSkill(int skill_number) {
        switch(skill_number){
            case 1: return skill1;
            case 2: return skill2;
            case 3: return skill3;
            case 4: return skill4;
        }
        return skill1;
    }

    public Pixmap getImage() {
        return texture;
    }
}
