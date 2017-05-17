package com.joeytman.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.joeytman.flappybird.FlappyDemo;
import com.joeytman.flappybird.sprites.Bird;
import com.joeytman.flappybird.sprites.Tube;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 250;
    private static final int TUBE_COUNT = 4;

    private int score;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        score = 0;
        bird = new Bird(50, 400);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("backgroundDistrib2.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth, 0);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth) + ground.getWidth(), 0);

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(350 + i * (TUBE_SPACING + Tube.TUBE_WIDTH), i));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for (Tube tube : tubes) {
            if (tube.getPosBotTube().x + tube.getBottomTube().getWidth() > bird.getLastPosition().x
                    && tube.getPosBotTube().x +tube.getBottomTube().getWidth() < bird.getPosition().x) {
                score += 1;
            }
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosBotTube().x + tube.TUBE_WIDTH) {
                tube.reposition(tube.getPosTopTube().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                lose();
                break;
            }
        }
        if (bird.getPosition().y <= ground.getHeight()) {
            lose();
        }
        cam.update();
    }

    private void lose() {
        gsm.set(new LoseState(gsm, score));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            tube.font.draw(sb, tube.num + "", tube.getPosBotTube().x + (tube.getTopTube().getWidth() / 2), tube.getPosBotTube().y + (tube.getBottomTube().getHeight() - 20));
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        for (Tube tube : tubes) {
            tube.font.draw(sb, tube.num + "", tube.getPosBotTube().x + (tube.getTopTube().getWidth() / 2), tube.getPosBotTube().y + (tube.getBottomTube().getHeight() - 20));
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        ground.dispose();
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
