package com.dankquest.game.com.dankquest.game.logic;

import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.actors.HeroActor;
import com.dankquest.game.com.dankquest.game.logic.skill.Skill;

import java.util.Iterator;
import java.util.Map;

public class Hero {

    Hero() { }
    public Hero(String name) {
        this.name = name;
    }
    Hero(Hero hero) {
        this.healthTotal = hero.healthTotal;
        this.healthCurrent = hero.healthCurrent;
        this.initiative = hero.initiative;
        this.attackDamage = hero.attackDamage;
        this.damageMultiplier = hero.damageMultiplier;
        this.baseShield = hero.baseShield;
        this.shield = hero.shield;
    }
    public String name;
    public HeroClass heroClass;
    public Pixmap texture;
    public Pixmap portrait;

    public double getHealthTotal() {
        return getBuffedHeroValues().healthTotal;
    }

    public double getHealthCurrent() {
        return healthCurrent;
    }

    public double getAttackDamage() {
        return getBuffedHeroValues().attackDamage;
    }

    public double getInitiative() {
        return getBuffedHeroValues().initiative;
    }

    public double getDamageMultiplier() {
        return getBuffedHeroValues().damageMultiplier;
    }

    public double getShield(){
        return getBuffedHeroValues().shield;
    }

    public double getBaseShield(){
        return getBuffedHeroValues().baseShield;
    }

    public void recieveDamage(double damageRecieved, boolean trueDamage){
        if(!trueDamage) {
            this.shield -= damageRecieved;
            if(this.shield < 0){
                this.healthCurrent += this.shield;
                this.shield = 0;
            }
        } else{
            this.healthCurrent -= damageRecieved;
        }
    }

    public double healthTotal;
    public double healthCurrent;
    public double attackDamage;
    public double initiative;
    public double damageMultiplier;
    public double shield, baseShield;
    public boolean isDed;

    public Skill skill1;
    public Skill skill2;
    public Skill skill3;
    public Skill skill4;
    public HeroActor heroActor;

    public Map<String, Buff> buffMap;

    public void reduceBuffDuration() {
        for(Map.Entry<String, Buff> buffEntry : buffMap.entrySet()) {
            buffEntry.getValue().buffDuration--;
        }
        Iterator<Map.Entry<String, Buff>> mapIterator = buffMap.entrySet().iterator();
        Map.Entry<String, Buff> mapEntry;
        while(mapIterator.hasNext()) {
            mapEntry = mapIterator.next();
            if(mapEntry.getValue().buffDuration <= 0) {
                mapIterator.remove();
            }
        }
    }

    private Hero getBuffedHeroValues() {
        Hero buffedHero = new Hero(this);
        for(Map.Entry<String, Buff> buffEntry : buffMap.entrySet()) {
            buffEntry.getValue().useBuff(buffedHero);
        }
        return buffedHero;
    }

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
