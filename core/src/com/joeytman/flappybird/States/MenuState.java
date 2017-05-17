package com.joeytman.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.joeytman.flappybird.FlappyDemo;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */
public class MenuState extends State {
    private Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

}

