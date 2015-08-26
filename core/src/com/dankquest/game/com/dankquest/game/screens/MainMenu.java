package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MainMenu extends BasicScreen {

    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide() {
        //Pamietamy ¿e na wszystkimi trzeba wywolywac .dispose()
    }
}