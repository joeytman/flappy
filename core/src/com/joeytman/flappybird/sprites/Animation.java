package com.joeytman.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xpath.internal.functions.FuncFloor;

/**
 * Created by Joey Thaidigsman on 5/17/2017.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        this.frameCount = frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame = (frame + 1) % frameCount;
            currentFrameTime = 0;
        }
    }
    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public void dispose() {
        frames = null;
    }
}
