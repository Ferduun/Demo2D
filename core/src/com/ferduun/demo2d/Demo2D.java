package com.ferduun.demo2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;

/**
 * This is the "entry point" for our game.
 *
 * Default class is ApplicationAdapter, we change that to Game
 * to allow usage of Screens.
 *
 * Game class has the following methods:
 * - create method which we use as constructor
 * - render method which we don't use (rendering is handled by Screens), !!DO NOT OVERRIDE!!
 * - dispose method which gets rid of assets once their lifecycle is over
 * - setScreen which sets the game to an argument Screen instance
 *
 */
public class Demo2D extends Game {

	@Override
	public void create() {

		Gdx.app.log("WARN", "Reached entrypoint!");

		VisUI.load(); //VisUI is an optional LibGdx library; it includes UI assets we can use

        GameScreen gameScreen = new GameScreen(this);

		this.setScreen(gameScreen); //We set the game to our Game Screen

	}

	@Override
	public void dispose() {

	}
}
