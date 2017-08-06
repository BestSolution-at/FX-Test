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

import javafx.scene.Scene;

/**
 * Factory to create a {@link RController}
 */
public interface RControllerFactory {
	/**
	 * Create a controller for the scene
	 * 
	 * @param s
	 *            the scene
	 * @return self
	 */
	public RController create(Scene s);
}
