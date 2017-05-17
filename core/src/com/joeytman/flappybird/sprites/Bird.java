package com.joeytman.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */

public class Bird {
    private static final int G = -15;
    private static final int MOVEMENT = 200;
    private Vector3 lastPosition;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;

    private Animation birdAnimation;

    private Rectangle bounds;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        lastPosition = new Vector3(position);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("hugBatchSmall.png");
        birdAnimation = new Animation(new TextureRegion(texture), 5, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 5, texture.getHeight());
    }

    public void update(float dt) {
        lastPosition = new Vector3(position);
        birdAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, G, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getLastPosition() {
        return lastPosition;
    }
    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 500;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        birdAnimation.dispose();
    }


}
