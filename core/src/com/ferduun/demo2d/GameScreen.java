package com.ferduun.demo2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;

/**
 * This is the "entry point" for our game.
 *
 * Default class is ApplicationAdapter, we change that to Game
 * to allow usage of Screens.
 *
 * Game class has the following methods:
 * - create method which we use as constructor
 * - render method which we don't use (rendering is handled by Screens)
 * - dispose method which gets rid of assets once their lifecycle is over
 * - setScreen which sets the game to an argument Screen instance
 *
 */
public class GameScreen implements Screen{

    Demo2D game;
    FitViewport vp;
    Stage stage;
    Image image;

    public GameScreen(Demo2D game){

        Gdx.app.log("WARN", "Reached main game class!");

        this.game = game;

        /**
         * We are going to use the Scene2D API in order to draw elements and handle input.
         *
         * Scene2D makes use of Stage objects to contain graphical elements, called Actors.
         * Stage implements the InputProcessor interface, which means it can be used to handle user input.
         *
         * An Actor can be anything, such as an Image, a Button, a Window...
         *
         * A Table Actor can also be used to lay out other Actors on a grid (for instance, User Interface).
         *
         * Scene2D makes very easy to add effects to Actors via Actions, which can be rotations, color change, custom functions...
         *
         */
        vp = new FitViewport(640, 480); //A viewport is the area where the game will be drawn. See the ViewPort page in the docs.
        stage = new Stage(vp); //A stage is a container for Actors, and is drawn on the argument ViewPort

        Table table = new Table(); //A table is initialized...
        table.setFillParent(true); //The table is set to fill the entire available drawing area (the Stage)

        /**
         * In this block of code we are going to create various Actors:
         * - an Image, which is an Actor containing a Texture.
         * - various TextButtons.
         *
         * TextButtons require a Skin, which is a file containing definitions for graphical elements (fonts, backgrounds...).
         * In our case, we are going to use the Skin provided by VisUI.
         *
         */

        /**
         * First, we load a picture as a Texture.
         *
         * The constructor takes a file pointer as an argument, whose path is the core/assets folder.
         * In an Android project, the folder is android/assets instead.
         *
         * A TextureAtlas can be used to reduce drawing calls, see documentation.
         *
         * Image constructor takes the texture as argument.
         * Finally we use the setOrigin method to set the rotation point to center.
         *
         */
        Texture t = new Texture(Gdx.files.internal("badlogic.jpg"));
        image = new Image(t);
        image.setOrigin(Align.center);

        //TextButton takes a String and a Skin as arguments; we are going to create three of them (set color, rotation, reset actions).
        TextButton blueButton = new TextButton("Set to BLUE", VisUI.getSkin());
        TextButton rotateButton = new TextButton("ROTATE", VisUI.getSkin());
        TextButton clearButton = new TextButton("RESET", VisUI.getSkin());

        //We add a ClickListener to the Actor and override its clicked method; this allows us to run a function on button click.
        blueButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){

                image.addAction(Actions.color(Color.BLUE)); //We add an Action to the Image to change its colour.

            }
        });

        rotateButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){

                image.addAction(Actions.rotateBy(360, 1)); //Action to do a full rotation over one second.

            }
        });

        clearButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){

                //Finally, we use clearActions() to stop the rotation, and reset the Image.
                image.clearActions();
                image.addAction(Actions.color(Color.WHITE));
                image.addAction(Actions.rotateTo(0));

            }
        });

        /**
         *
         * A Table will be used to lay out our Image and TextButtons over a grid.
         *
         * The Table will then be added to the Stage and displayed on screen.
         *
         */
        table.add(image).colspan(2); //The Image is added, it will span two columns
        table.defaults().width(100); //All buttons will have a default width of 100px
        table.row().padTop(25); //A new row of elements is created with a 25px distance from top
        table.add(blueButton); //blueButton and rotateButton are added on the same row, which creates two columns on the table
        table.add(rotateButton);
        table.row().padTop(25);
        table.add(clearButton).colspan(2);


        stage.addActor(table); //The table is added to the stage...

        Gdx.input.setInputProcessor(stage); //...finally, LibGdx is told to let Stage handle user input.

    }

    //Show method is used to start music etc.
    @Override
    public void show() {

    }

    //Render is used to update game logic and draw to screen, and has a delta argument, which is the amount of milliseconds from last frame
    @Override
    public void render(float delta) {

        //First we clear the screen
        Gdx.gl.glClearColor(199/255f, 251/255f, 253/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(); //act method of Stage will call the act method of all children Actors; act updates the status of an actor (updates its Actionns and logic)
        stage.draw(); //finally, we draw

    }

    @Override
    public void resize(int width, int height) {

        vp.update(width, height); //on screen resize, viewport is resized as well

    }

    /**
     * pause resume and hide are methods called by android when the app is minimized and resume.
     *
     * they are used to reload assets to memory.
     *
     */
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();

    }
}