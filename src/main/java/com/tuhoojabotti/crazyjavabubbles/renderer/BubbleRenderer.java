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
package com.tuhoojabotti.crazyjavabubbles.renderer;

import static com.tuhoojabotti.crazyjavabubbles.Util.curveValue;
import com.tuhoojabotti.crazyjavabubbles.logic.Bubble;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Renders the {@link Bubble} with cool physics.
 * @author Ville Lahdenvuo <tuhoojabotti@gmail.com>
 */
public class BubbleRenderer {

    private final Graphics gfx;
    private final int r = RenderSettings.BUBBLE_RADIUS;
    private final Vector2f mousePosition;
    private Bubble bubble;
    private Vector2f velocity;
    private Vector2f target;
    private Circle outCircle;
    private Circle inCircle;
    private Random rand;

    /**
     * Create a {@link Bubble} renderer with mouse interaction.
     *
     * @param bubble the bubble to render
     * @param gfx graphics controller
     * @param mouse mouse position
     */
    public BubbleRenderer(Bubble bubble, Graphics gfx, Vector2f mouse) {
        rand = new Random();
        this.bubble = bubble;
        this.gfx = gfx;
        mousePosition = mouse;

        velocity = new Vector2f();
        target = new Vector2f();

        outCircle = new Circle(0, 0, r / 2, 17);
        inCircle = new Circle(0, 0, r / 3, 17);

        outCircle.setLocation(RenderSettings.BOARD_MARGIN + bubble.x * r
                + 100 - rand.nextInt(200), -10);
    }

    /**
     * Create a {@link Bubble} renderer without mouse interaction.
     *
     * @param bubble the bubble to render
     * @param gfx graphics controller
     */
    public BubbleRenderer(Bubble bubble, Graphics gfx) {
        this(bubble, gfx, new Vector2f(-100000, -100000));
    }

    public void applyForce(Vector2f bubble) {
        int m = RenderSettings.BOARD_MARGIN;
        Vector2f point = new Vector2f(m + bubble.x * r, m + bubble.y * r);
        double angle = Math.atan2(point.y - outCircle.getCenterY(), point.x - outCircle.getCenterX());
        float distance = Math.max(10, point.distance(outCircle.getLocation()));

        velocity.x -= 3 / distance * (float) Math.cos(angle) * 200;
        velocity.y -= 2 / distance * (float) Math.sin(angle) * 200;
    }

    /**
     * Render a {@link Bubble}.
     *
     * @param x board's x-coordinate
     * @param y board's y-coordinate
     */
    public void render(int x, int y) {
        target.set(x + bubble.x * r, y + bubble.y * r);

        // Outer circle
        gfx.setColor(bubble.getColor());
        gfx.fill(outCircle);
        // Inner circle
        gfx.setColor(Color.black);
        gfx.fill(inCircle);

        // Debug box
        if (RenderSettings.DEBUG) {
            gfx.setColor(Color.gray);
            gfx.drawRect(x + bubble.x * r, y + bubble.y * r, r, r);
            gfx.drawLine(x + bubble.x * r + r / 2, y + bubble.y * r + r / 2,
                    outCircle.getCenterX(), outCircle.getCenterY());
        }
    }

    /**
     * Update the renderer.
     * 
     * @param gc game container
     * @param delta delta time
     * @return is bubble popped
     */
    public boolean update(GameContainer gc, int delta) {
        float smooth = RenderSettings.BUBBLE_WOBBLE + bubble.y / 80f;
        int selected = bubble.isSelected() ? 1 : 0;
        float mouseDistance = mousePosition.distance(outCircle.getLocation());
        double mouseAngle = Math.atan2(
                mousePosition.y - outCircle.getCenterY(),
                mousePosition.x - outCircle.getCenterX()
        );

        // Update velocity towards real position + towards mouse cursor.
        velocity.x = curveValue(target.x - outCircle.getX(), velocity.x, smooth)
                + 0.35f * (float) Math.cos(mouseAngle);
        velocity.y = curveValue(target.y - outCircle.getY(), velocity.y, smooth)
                + 0.35f * (float) Math.sin(mouseAngle);

        // Move Bubble according to it's velocity.
        outCircle.setLocation(
                outCircle.getLocation()
                .add(velocity.scale(delta * 0.04f))
        );

        // Set hole size. It depends on mouse distance and selection.
        inCircle.setRadius((float) (Math.max(8, r / 3f - 10 / mouseDistance * 50)
                + Math.sin((gc.getTime() / 80.0) + (double) (bubble.x + bubble.y)) * selected * 2.5f));

        // Update the hole position.
        float hole = 3.5f * (1 - selected), offset = r / 2 - inCircle.getRadius();
        inCircle.setLocation(
                outCircle.getLocation()
                .add(new Vector2f(
                                ((float) Math.cos(mouseAngle) * hole) + offset,
                                ((float) Math.sin(mouseAngle) * hole) + offset)
                )
        );

        return bubble.isPopped();
    }
}
