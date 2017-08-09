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

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.fx.core.ServiceUtils;
import org.eclipse.fx.core.ThreadSynchronize;
import org.eclipse.fx.core.ThreadSynchronize.BlockCondition;
import org.eclipse.fx.ui.controls.JavaFXCompatUtil;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Robot;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;

import at.bestsolution.fx.test.rcontrol.RController;
import at.bestsolution.fx.test.rcontrol.RNode;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination.ModifierValue;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

@SuppressWarnings({ "restriction", "javadoc" })
public class FXRobotRemoteController implements RController {
	private final Scene scene;
	private final Robot glassRobot;
	private final FXRobot fxRobot;
	private final ThreadSynchronize threadSynchronize;

	public FXRobotRemoteController(Scene scene) {
		this.scene = scene;
		this.glassRobot = Application.GetApplication().createRobot();
		this.fxRobot = FXRobotFactory.createRobot(scene);
		this.threadSynchronize = ServiceUtils.getService(ThreadSynchronize.class).orElse(null);
	}

	@Override
	public RController waitForRender() {
		sleep(2000);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Node> Stream<RNode<T>> css(String selector) {
		if (scene.getRoot() != null) {
			return scene.getRoot().lookupAll(selector).stream().map(v -> new RNodeImpl<>((T) v, this));
		}
		return Stream.empty();
	}

	@Override
	public <T extends Node> Stream<RNode<T>> css(Class<T> type, String selector) {
		return this.<T>css(selector).filter(r -> type.isAssignableFrom(r.node().getClass()));
	}

	@Override
	public <T extends Node> Optional<RNode<T>> cssFirst(String selector) {
		return this.<T>css(selector).findFirst();
	}

	@Override
	public <T extends Node> Optional<RNode<T>> cssFirst(Class<T> type, String selector) {
		return this.<T>css(type, selector).findFirst();
	}

	@Override
	public RController click(MouseButton button) {
		glassRobot.mousePress(button.ordinal());
		glassRobot.mouseRelease(button.ordinal());
		sleep(100);
		return this;
	}

	@Override
	public RController moveToScreen(double x, double y) {
		glassRobot.mouseMove((int) x, (int) y);
		sleep(100);
		return this;
	}
	
	@Override
	public RController moveToScreen(double x, double y, Duration duration) {
		BlockCondition<Void> b = new BlockCondition<>();
		int mouseX = glassRobot.getMouseX();
		int mouseY = glassRobot.getMouseY();
		double dx = x - mouseX;
		double dy = y - mouseY;
		Transition tt = new Transition() {
			{
				setCycleDuration(duration);
			}
			@Override
			protected void interpolate(double frac) {
				glassRobot.mouseMove((int)(mouseX + dx * frac), (int)(mouseY + dy * frac));
			}
		};
		tt.setOnFinished( e -> b.release(null));
		tt.play();
		this.threadSynchronize.block(b);
		sleep(100);
		return this;
	}
	
	@Override
	public RController type(KeyCode... codes) {
		for( KeyCode code : codes ) {
			int c = JavaFXCompatUtil.getCode(code);
			glassRobot.keyPress(c);
			glassRobot.keyRelease(c);			
		}
		sleep(100);
		return this;
	}
	
	@Override
	public RController type(KeyCodeCombination... combinations) {
		for( KeyCodeCombination combination : combinations ) {
			if( combination.getAlt() == ModifierValue.DOWN ) {
				glassRobot.keyPress(JavaFXCompatUtil.getCode(KeyCode.ALT));
			}
			if( combination.getShift() == ModifierValue.DOWN ) {
				glassRobot.keyPress(JavaFXCompatUtil.getCode(KeyCode.SHIFT));
			}
			if( combination.getControl() == ModifierValue.DOWN ) {
				glassRobot.keyPress(JavaFXCompatUtil.getCode(KeyCode.CONTROL));
			}
			
			glassRobot.keyPress(JavaFXCompatUtil.getCode(combination.getCode()));
			glassRobot.keyRelease(JavaFXCompatUtil.getCode(combination.getCode()));
			
			if( combination.getAlt() == ModifierValue.DOWN ) {
				glassRobot.keyRelease(JavaFXCompatUtil.getCode(KeyCode.ALT));
			}
			if( combination.getShift() == ModifierValue.DOWN ) {
				glassRobot.keyRelease(JavaFXCompatUtil.getCode(KeyCode.SHIFT));
			}
			if( combination.getControl() == ModifierValue.DOWN ) {
				glassRobot.keyRelease(JavaFXCompatUtil.getCode(KeyCode.CONTROL));
			}
			
		}
		sleep(100);
		return this;
	}
	
	@Override
	public RController typeText(String text) {
		for( int i = 0; i < text.length(); i++ ) {
			fxRobot.keyType(KeyCode.UNDEFINED,text.substring(i, i+1));
		}
		sleep(100);
		return this;
	}
	
	@Override
	public RController drag(double fromX, double fromY, double toX, double toY) {
		moveToScreen(fromX, fromY);
		glassRobot.mousePress(MouseButton.PRIMARY.ordinal());
		moveToScreen(toX, toY);
		glassRobot.mouseRelease(MouseButton.PRIMARY.ordinal());
		sleep(100);
		return this;
	}
	
	public RController sleep(Duration duration) {
		return sleep((long)duration.toMillis());
	}
	
	@Override
	public RController run(Consumer<RController> c) {
		c.accept(this);
		return this;
	}
	
	@Override
	public <R> R run(Function<RController, R> f) {
		return f.apply(this);
	}
	
	@Override
	public RController run(Runnable r) {
		r.run();
		return this;
	}
	
	private RController sleep(long millis) {
		BlockCondition<Void> b = new BlockCondition<>();
		this.threadSynchronize.scheduleExecution(millis, () -> b.release(null));
		this.threadSynchronize.block(b);
		return this;
	}
}