package com.dankquest.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dankquest.game.com.dankquest.game.screens.MainMenu;
import com.dankquest.game.com.dankquest.game.util.Assets;

public class GameCore extends Game {
	@Override
	public void create () {
		setScreen(new MainMenu(this));
	}
}