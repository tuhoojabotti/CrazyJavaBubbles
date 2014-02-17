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
package com.tuhoojabotti.crazyjavabubbles.gui;

/**
 * Full of constants for rendering the game.
 * @author Ville Lahdenvuo <tuhoojabotti@gmail.com>
 */
public class RenderSettings {

    /**
     * Size of the {@link Bubble}s.
     */
    public static final int BUBBLE_RADIUS = 32;
    
    /**
     * How much do the bubbles move?
     */
    public static final float BUBBLE_WOBBLE = 0.04f;

    /**
     * Margin where to draw the {@link Board}.
     */
    public static final int BOARD_MARGIN = 15;
    
    /**
     * Enable or disable particle effects.
     * They will be disabled in any case if fps is under 100.
     */
    public static boolean PARTICLE_EFFECTS = true;
    
    /**
     * Render debug stuff on the screen.
     */
    public static final boolean DEBUG = false;
    
    /**
     * Width of the window.
     */
    public static final int SCREEN_WIDTH = 800;

    /**
     * Height of the window.
     */
    public static final int SCREEN_HEIGHT = 600;    
    
    /**
     * Should the game run fullscreen.
     */
    public static boolean IS_FULLSCREEN = false;
}