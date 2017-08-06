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
package at.bestsolution.fx.test.junit;

import org.eclipse.fx.core.Status;
import org.junit.Assert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public abstract class BaseTestFXComponentTest extends FXComponentTest<BorderPane, BaseTestFXComponentTest.LoginComponent> {
	public BaseTestFXComponentTest() {
		super(BorderPane.class);
	}

	@Override
	protected LoginComponent createComponent(BorderPane parent) {
		return new LoginComponent(parent);
	}

	public static class LoginComponent {
		private Status status;
		private TextField username;
		private PasswordField password;

		public LoginComponent(BorderPane p) {
			GridPane g = new GridPane();

			{
				g.add(new Label("Username"), 0, 0);
				username = new TextField();
				g.add(username, 1, 0);
			}

			{
				g.add(new Label("Password"), 0, 1);
				password = new PasswordField();
				g.add(password, 1, 1);
			}

			Button button = new Button("Login");
			button.setOnAction(e -> {
				if( username.getText().equals("tom@bestsolution.at") ) {
					if( password.getText().equals("test") ) {
						status = Status.ok();
					} else {
						status = Status.status(Status.State.ERROR, 2, "Invalid password for '"+username.getText()+"'", null);
					}
				} else {
					status = Status.status(Status.State.ERROR, 1, "Unknown username", null);
				}
			});
			g.add(button, 1, 2);
			g.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
			g.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
			p.setCenter(g);
		}
	}

	public void testInvalidUsername() {
		Assert.assertNull(component().status);
		rcontroller().cssFirst(".button").get().click();
		Assert.assertNotNull(component().status);
		Assert.assertEquals(1, component().status.getCode());
	}
	
	public void testInvalidPassword() {
		Assert.assertNull(component().status);
		rcontroller().cssFirst(".text-field").get().typeText("tom@bestsolution.at");
		rcontroller().cssFirst(".button").get().click();
		Assert.assertNotNull(component().status);
		Assert.assertEquals(2, component().status.getCode());
		Assert.assertEquals("Invalid password for 'tom@bestsolution.at'", component().status.getMessage());
	}
	
	public void testSuccessPassword() {
		Assert.assertNull(component().status);
		rcontroller().cssFirst(".text-field").get().typeText("tom@bestsolution.at");
		rcontroller().cssFirst(".password-field").get().typeText("test");
		rcontroller().cssFirst(".button").get().click();
		Assert.assertNotNull(component().status);
		Assert.assertTrue(component().status.isOk());
	}
}
