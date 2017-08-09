/*******************************************************************************
 * Copyright (c) 2017 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.fx.test.rcontrol;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

/**
 * Remote control a JavaFX UI
 */
public interface RController {
	/**
	 * Query for the first {@link Node} using a CSS-Selector using
	 * {@link Node#lookupAll(String)}
	 * 
	 * @param selector
	 *            the selector
	 * @return query result
	 */
	public <T extends Node> Optional<RNode<T>> cssFirst(String selector);

	/**
	 * Query for the first {@link Node} using a CSS-Selector using
	 * {@link Node#lookupAll(String)}
	 * 
	 * @param type
	 *            the {@link Node} type
	 * 
	 * @param selector
	 *            the selector
	 * @return query result
	 */
	public <T extends Node> Optional<RNode<T>> cssFirst(Class<T> type, String selector);

	/**
	 * Query for all {@link Node} nodes using a CSS-Selector using
	 * {@link Node#lookupAll(String)}
	 * 
	 * @param selector
	 *            the selector
	 * @return stream with query result
	 */
	public <T extends Node> Stream<RNode<T>> css(String selector);

	/**
	 * Query for all {@link Node} nodes using a CSS-Selector using
	 * {@link Node#lookupAll(String)}
	 * 
	 * @param type
	 *            the {@link Node} type
	 * 
	 * @param selector
	 *            the selector
	 * @return stream with query result
	 */
	public <T extends Node> Stream<RNode<T>> css(Class<T> type, String selector);

	/**
	 * Wait for the UI to render
	 * 
	 * @return self
	 */
	public RController waitForRender();

	/**
	 * Click the mouse button at the specified position
	 * 
	 * @param button
	 *            the button to click
	 * @return self
	 */
	public RController click(MouseButton button);

	/**
	 * Move the cursor to the provided position on the screen
	 * 
	 * @param x
	 *            the x position on the screen
	 * @param y
	 *            the y position on the screen
	 * @param duration
	 *            the time the used to move from the current to the requested
	 *            position
	 * @return self
	 */
	public RController moveToScreen(double x, double y, Duration duration);

	/**
	 * Move the cursor to the provided position on the screen
	 * 
	 * @param x
	 *            the x position on the screen
	 * @param y
	 *            the y position on the screen
	 * @return self
	 */
	public RController moveToScreen(double x, double y);

	/**
	 * Type the provided key codes
	 * 
	 * @param codes
	 *            the key codes
	 * @return self
	 */
	public RController type(KeyCode... codes);

	/**
	 * Type the provided key combination
	 * 
	 * @param combinations
	 *            the key combination
	 * @return self
	 */
	public RController type(KeyCodeCombination... combinations);

	/**
	 * Type the provided text
	 * 
	 * @param text
	 *            the text
	 * @return self
	 */
	public RController typeText(String text);

	/**
	 * Drag from the source to the target coordinates
	 * 
	 * @param fromX
	 *            the x coordinate on the screen the drag starts
	 * @param fromY
	 *            the y coordinate on the screen the drag starts
	 * @param toX
	 *            the x coordinate on the screen the drag ends
	 * @param toY
	 *            the y coordinate on the screen the drag ends
	 * @return self
	 */
	public RController drag(double fromX, double fromY, double toX, double toY);

	/**
	 * Run the provided code
	 * 
	 * @param r
	 *            the runnable
	 * @return self
	 */
	public RController run(Runnable r);

	/**
	 * Run the provided code
	 * 
	 * @param c
	 *            the consumer
	 * @return self
	 */
	public RController run(Consumer<RController> c);

	/**
	 * Run the provided code
	 * 
	 * @param f
	 *            the function
	 * @return the result of the function
	 */
	public <R> R run(Function<RController, R> f);

	/**
	 * Sleep for the specified duration (without halting the event loop)
	 * 
	 * @param duration
	 *            the duration
	 * @return self
	 */
	public RController sleep(Duration duration);

}
