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

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import at.bestsolution.fx.test.junit.internal.ApplicationLaunch;

public class FXRunner extends BlockJUnit4ClassRunner {

	public FXRunner(Class<?> klass) throws InitializationError {
		super(klass);
		ApplicationLaunch.bootstrap();
	}

	
	@Override
	protected Statement methodBlock(FrameworkMethod method) {
		Statement statement = super.methodBlock(method);
		return new FXTestStatement(statement);
	}
}
