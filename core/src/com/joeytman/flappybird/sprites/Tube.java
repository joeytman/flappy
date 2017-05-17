package com.joeytman.flappybird.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.Random;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */

public class Tube {
    public static final int TUBE_WIDTH = 144;
    private static final int FLUCT = 220;
    private static final int TUBE_GAP = 250;
    private static final int LOWEST_OPENING = 50;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;
    public BitmapFont font;
    public int num;

    public Tube(float x) {
        rand = new Random();
        topTube = new Texture("topTubeRed.png");
        bottomTube = new Texture("botTubeBlue.png");
        posTopTube = new Vector2(x, rand.nextInt(FLUCT) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public Tube(float x, int num) {
        this(x);
        this.num = num;
        font = new BitmapFont();
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCT) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        boundsBot = null;
        boundsTop = null;
        topTube.dispose();
        bottomTube.dispose();
        font.dispose();
    }
}
