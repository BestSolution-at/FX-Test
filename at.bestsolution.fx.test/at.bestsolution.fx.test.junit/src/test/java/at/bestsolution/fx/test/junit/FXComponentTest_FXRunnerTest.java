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

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(FXRunner.class)
public class FXComponentTest_FXRunnerTest extends BaseTestFXComponentTest {
	@Test
	@Override
	public void testInvalidPassword() {
		super.testInvalidPassword();
	}
	
	@Test
	@Override
	public void testInvalidUsername2() {
		super.testInvalidUsername2();
	}
	
	@Test
	@Override
	public void testInvalidUsername() {
		super.testInvalidUsername();
	}
	
	@Test
	@Override
	public void testSuccessPassword() {
		super.testSuccessPassword();
	}
}
