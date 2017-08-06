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
package at.bestsolution.fx.test.rcontrol.impl;

import at.bestsolution.fx.test.rcontrol.RController;
import at.bestsolution.fx.test.rcontrol.RNode;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;

@SuppressWarnings("javadoc")
public class RNodeImpl<T extends Node> implements RNode<T> {
	private final T node;
	private final RController controller;
	
	public RNodeImpl(T node, RController controller) {
		this.node = node;
		this.controller = controller;
	}
	
	public T node() {
		return node;
	}

	private RNode<T> _click(MouseButton button) {
		center();
		controller.click(button);
		return this;
	}
	
	private RNode<T> _click(MouseButton button, double x, double y) {
		moveTo(x, y);
		controller.click(button);
		return this;
	}
	
	@Override
	public RNode<T> click() {
		return _click(MouseButton.PRIMARY);
	}
	
	@Override
	public RNode<T> click(double x, double y) {
		return _click(MouseButton.PRIMARY, x, y);
	}
	
	@Override
	public RNode<T> rightClick() {
		return _click(MouseButton.SECONDARY);
	}
	
	@Override
	public RNode<T> rightClick(double x, double y) {
		return _click(MouseButton.SECONDARY, x, y);
	}
	
	@Override
	public RNode<T> typeText(String text) {
		focus();
		controller.typeText(text);
		return this;
	}
	
	@Override
	public RNode<T> moveTo(double x, double y) {
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		controller.moveToScreen(bounds.getMinX() + x, bounds.getMinY() + y);
		return this;
	}
	
	@Override
	public RNode<T> center() {
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		moveTo(bounds.getWidth() / 2, bounds.getHeight() / 2);
		return this;
	}
	
	@Override
	public RNode<T> position(Pos pos) {
		double x;
		double y;
		
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		switch (pos.getHpos()) {
		case CENTER:
			x = bounds.getMinX() + bounds.getWidth()/2;
			break;
		case RIGHT:
			x = bounds.getMaxX();
			break;
		default:
			x = bounds.getMinX();
			break;
		}
		
		switch (pos.getVpos()) {
		case BOTTOM:
		case BASELINE:
			y = bounds.getMaxY();
			break;
		case CENTER:
			y = bounds.getMinY() + bounds.getHeight()/2;
			break;
		default:
			y = bounds.getMinY();
			break;
		}
		
		controller.moveToScreen(x, y);
		return this;
	}
	
	@Override
	public RNode<T> focus() {
		node.requestFocus();
		return this;
	}
}
