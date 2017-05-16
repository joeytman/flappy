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
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, 0, 200, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        int menuBtnHeight = FlappyDemo.HEIGHT / 11;
        int menuBtnWidth = FlappyDemo.WIDTH / 4;
        sb.draw(playBtn, (FlappyDemo.WIDTH / 2) - (menuBtnWidth / 2), (FlappyDemo.HEIGHT / 2) - (menuBtnHeight / 2), menuBtnWidth, menuBtnHeight);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }

}

