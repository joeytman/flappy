package com.joeytman.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State newState) {
        states.pop().dispose();
        states.push(newState);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

}
