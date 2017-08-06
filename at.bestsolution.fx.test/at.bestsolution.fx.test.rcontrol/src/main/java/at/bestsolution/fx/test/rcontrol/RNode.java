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

import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * Wrapper for a remote controlled node
 * 
 * @param <T>
 */
public interface RNode<T extends Node> {
	/**
	 * @return the {@link Node}
	 */
	public T node();

	/**
	 * Click on the center of the {@link Node}
	 * 
	 * @return self
	 */
	public RNode<T> click();

	/**
	 * Click on the offset from the upper right corner of the the {@link Node}
	 * 
	 * @param x
	 *            the x offset from the upper left corner
	 * @param y
	 *            the y offset from the upper left corner
	 * @return self
	 */
	public RNode<T> click(double x, double y);

	/**
	 * Right click on he center of the {@link Node}
	 * 
	 * @return self
	 */
	public RNode<T> rightClick();

	/**
	 * Right-Click on the offset from the upper right corner of the the {@link Node}
	 * 
	 * @param x
	 *            the x offset from the upper left corner
	 * @param y
	 *            the y offset from the upper left corner
	 * @return self
	 */
	public RNode<T> rightClick(double x, double y);

	/**
	 * Type the provided text
	 * 
	 * @param text
	 *            the text
	 * @return self
	 */
	public RNode<T> typeText(String text);

	/**
	 * @return try to focus the node
	 */
	public RNode<T> focus();

	/**
	 * Move the cursor on the center of the {@link Node}
	 * 
	 * @return self
	 */
	public RNode<T> center();

	/**
	 * Position the move on the provided position on the control
	 * 
	 * @param pos
	 *            the position
	 * @return self
	 */
	public RNode<T> position(Pos pos);

	/**
	 * Move mouse cursor to a position relative to the upper left corner of the
	 * {@link Node}
	 * 
	 * @param x
	 *            the x offset from the upper left corner
	 * @param y
	 *            the y offset from the upper left corner
	 * @return self
	 */
	public RNode<T> moveTo(double x, double y);
}
