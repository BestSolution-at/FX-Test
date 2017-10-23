package at.bestsolution.fx.test.rcontrol.impl;

import java.util.Optional;
import java.util.stream.Stream;

import at.bestsolution.fx.test.rcontrol.Query;
import at.bestsolution.fx.test.rcontrol.RController;
import at.bestsolution.fx.test.rcontrol.RNode;
import javafx.scene.Node;

public class CSSQuery<T extends Node> implements Query<T, String> {
	private final Node context;
	private final String selector;
	private final RController controller;
	
	public CSSQuery(RController controller, Node context, String selector) {
		this.controller = controller;
		this.context = context;
		this.selector = selector;
	}
	
	@Override
	public RNode<T> first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RNode<T>> firstOpt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<RNode<T>> all() {
		if (context != null) {
			return context.lookupAll(selector).stream().map(v -> new RNodeImpl<>((T) v, controller));
		}
		return Stream.empty();
	}
}
