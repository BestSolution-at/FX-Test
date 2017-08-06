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

public interface RController {
	public <T extends Node> Optional<RNode<T>> cssFirst(String selector);

	public <T extends Node> Optional<RNode<T>> cssFirst(Class<T> type, String selector);

	public <T extends Node> Stream<RNode<T>> css(String selector);

	public <T extends Node> Stream<RNode<T>> css(Class<T> type, String selector);

	public RController waitForRender();

	public RController click(MouseButton button);

	public RController moveToScreen(double x, double y, Duration duration);

	public RController moveToScreen(double x, double y);

	public RController type(KeyCode... codes);
	
	public RController type(KeyCodeCombination... combinations);
	
	public RController typeText(String text);
	
	public RController run(Runnable r);

	public RController run(Consumer<RController> c);
	
	public RController sleep(Duration duration);
	
	public <R> R run(Function<RController, R> f);
}
