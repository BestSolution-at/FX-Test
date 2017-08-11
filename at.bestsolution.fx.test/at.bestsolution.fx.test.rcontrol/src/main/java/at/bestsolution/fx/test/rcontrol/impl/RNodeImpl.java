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

import at.bestsolution.fx.test.rcontrol.Click;
import at.bestsolution.fx.test.rcontrol.Drag;
import at.bestsolution.fx.test.rcontrol.Move;
import at.bestsolution.fx.test.rcontrol.RController;
import at.bestsolution.fx.test.rcontrol.RNode;
import at.bestsolution.fx.test.rcontrol.Type;
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
		controller.run(Click.click(button));
		return this;
	}
	
	private RNode<T> _click(MouseButton button, double x, double y) {
		controller.run(Click.click(button, x, y));
		return this;
	}
	
	@Override
	public RNode<T> click() {
		return _click(MouseButton.PRIMARY);
	}
	
	@Override
	public RNode<T> click(MouseButton button) {
		return _click(button);
	}
	
	@Override
	public RNode<T> click(double x, double y) {
		return _click(MouseButton.PRIMARY, x, y);
	}
		
	@Override
	public RNode<T> click(MouseButton button, double x, double y) {
		return _click(button, x, y);
	}
	
	@Override
	public RNode<T> doubleClick() {
		return doubleClick(MouseButton.PRIMARY);
	}
	
	@Override
	public RNode<T> doubleClick(MouseButton button) {
		_click(button);
		_click(button);
		return this;
	}
	
	@Override
	public RNode<T> doubleClick(double x, double y) {
		return doubleClick(MouseButton.PRIMARY, x, y);
	}
		
	@Override
	public RNode<T> doubleClick(MouseButton button, double x, double y) {
		_click(button,x,y);
		_click(button,x,y);
		return this;
	}
	
	@Override
	public RNode<T> typeText(String text) {
		focus();
		controller.run(Type.text(text));
		return this;
	}
	
	@Override
	public RNode<T> moveTo(double x, double y) {
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		controller.run(Move.to(bounds.getMinX() + x, bounds.getMinY() + y));
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
		
		controller.run(Move.to(x, y));
		return this;
	}
	
	@Override
	public RNode<T> focus() {
		node.requestFocus();
		return this;
	}
	
	@Override
	public RNode<T> dragTo(double x, double y) {
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		controller.run(Drag.to(bounds.getWidth() / 2, bounds.getHeight() / 2, x, y));
		return this;
	}
	
	@Override
	public RNode<T> dragBy(double dx, double dy) {
		Bounds bounds = node.localToScreen(node.getBoundsInLocal());
		double x = bounds.getMinX() + bounds.getWidth() / 2;
		double y = bounds.getMinY() + bounds.getHeight() / 2;
		controller.run(Drag.by(x, y, dx, dy));
		return this;
	}
}
