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

import javafx.scene.input.MouseButton;

/**
 * Drag operations
 */
public interface Drag {
	/**
	 * Create an operation dragging from an absolute position to another absolute
	 * position
	 * 
	 * @param fromX
	 *            the start x coordinate on screen
	 * @param fromY
	 *            the start y coordinate on screen
	 * @param toX
	 *            the end x coordinate on screen
	 * @param toY
	 *            the end y coordinate on screen
	 * @return the operation
	 */
	public static Operation to(double fromX, double fromY, double toX, double toY) {
		return (r) -> {
			r.mouseMoveTo((int) fromX, (int) fromY);
			r.press(MouseButton.PRIMARY);
			r.mouseMoveTo((int) toX, (int) toY);
			r.release(MouseButton.PRIMARY);
			return r;
		};

	}

	/**
	 * Create an operation dragging from an absolute position to another absolute
	 * position
	 * 
	 * @param fromX
	 *            the start x coordinate on screen
	 * @param fromY
	 *            the start y coordinate on screen
	 * @param dx
	 *            delta of the x coordinate
	 * @param dy
	 *            delta of the y coordinate
	 * @return the operation
	 */
	public static Operation by(double fromX, double fromY, double dx, double dy) {
		return (r) -> {
			r.mouseMoveTo((int) fromX, (int) fromY);
			r.press(MouseButton.PRIMARY);
			r.mouseMoveTo((int) (fromX + dx), (int) (fromY + dy));
			r.release(MouseButton.PRIMARY);
			return r;
		};
	}

	/**
	 * Drag actions
	 * 
	 * @param <C>
	 *            the owner
	 */
	public interface Dragable<C> {
		/**
		 * Drag from the center to the provided position
		 * 
		 * @param x
		 *            the x coordinate on screen
		 * @param y
		 *            the y coordinate on screen
		 * @return the owner
		 */
		public C dragTo(double x, double y);
		
		/**
		 * Drag from the center to the provided position
		 * 
		 * @param dx
		 *            the x delta from the center of the owner
		 * @param dy
		 *            the y delta from the center of the owner
		 * @return the owner
		 */
		public C dragBy(double dx, double dy);
	}
}
