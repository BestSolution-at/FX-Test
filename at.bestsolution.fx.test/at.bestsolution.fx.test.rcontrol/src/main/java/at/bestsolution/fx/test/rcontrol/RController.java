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
	 * Run the provided operations
	 * 
	 * @param r
	 *            operations to run
	 * @return self
	 */
	public RController run(Operation... r);

	/**
	 * @return instance to generate nativ events like mouse-events, key-events
	 */
	public REventGenerator eventGenerator();
}
