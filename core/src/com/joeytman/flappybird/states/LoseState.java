package com.joeytman.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.joeytman.flappybird.FlappyDemo;

/**
 * Created by Joey Thaidigsman on 5/17/2017.
 */

public class LoseState extends State {
    private Texture losebg;
    private int finalscore;
    public LoseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        cam.update();
        losebg = new Texture("code_right_pupper.jpg");

    }

    public LoseState(GameStateManager gsm, int finalScore) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        cam.update();
        losebg = new Texture("code_right_pupper.jpg");

        finalscore = finalScore;
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(losebg, 0, 0);
        BitmapFont font = new BitmapFont();
        font.getData().scale(10f);
        font.setColor(new Color(0f, 204f, 204f, 255f));
        font.draw(sb, "" + finalscore, 200, 700);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
