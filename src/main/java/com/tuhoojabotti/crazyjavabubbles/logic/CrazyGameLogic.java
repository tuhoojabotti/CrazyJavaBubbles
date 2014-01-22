/*
 * The MIT License
 *
 * Copyright 2014 Ville Lahdenvuo <tuhoojabotti@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tuhoojabotti.crazyjavabubbles.logic;

import java.awt.Point;

/**
 *
 * @author Ville Lahdenvuo <tuhoojabotti@gmail.com>
 */
public class CrazyGameLogic {

    private int score;
    private final Board board;

    /**
     * Create new {@link CrazyGame} logic.
     */
    public CrazyGameLogic() {
        board = new Board(24, 17);
    }

    /**
     * Initialise the logic.
     */
    public void init() {
        board.init();
        score = 0;
    }

    /**
     * @return whether game is over.
     */
    public boolean isGameOver() {
        return !board.hasMoreMoves();
    }
    
    /**
     * Returns score of the game.
     *
     * @return score of the game
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Pop the currently selected {@link Bubble}s.
     */
    public void pop() {
        int bubbles = board.pop();
        
        // Update score.
        if (bubbles > 0) {
            score += bubbles;
        }
    }

    /**
     * Update selection on the board.
     * @param point where the selection starts
     */
    public void select(Point point) {
        board.select(point.x, point.y);
    }
}
