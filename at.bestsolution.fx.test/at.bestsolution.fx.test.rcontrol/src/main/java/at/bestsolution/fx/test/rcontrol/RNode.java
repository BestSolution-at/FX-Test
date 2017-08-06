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

public interface RNode<T> {
	public T node();
	
	public RNode<T> click();
	public RNode<T> click(double x, double y);
	
	public RNode<T> rightClick();
	public RNode<T> rightClick(double x, double y);
	
	public RNode<T> typeText(String text);
	
	public RNode<T> focus();
	
	public RNode<T> center();
	public RNode<T> position(Pos pos);
	public RNode<T> moveTo(double x, double y);
}
