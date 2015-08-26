package com.dankquest.game;

import com.badlogic.gdx.Game;
import com.dankquest.game.com.dankquest.game.screens.MainMenu;

public class GameCore extends Game {
	@Override
	public void create () {
		setScreen(new MainMenu(this));
	}
}