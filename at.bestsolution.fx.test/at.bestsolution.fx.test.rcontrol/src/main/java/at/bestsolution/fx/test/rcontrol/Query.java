package at.bestsolution.fx.test.rcontrol;

import java.util.Optional;
import java.util.stream.Stream;

import javafx.scene.Node;

public interface Query<T extends Node,P> {
	public RNode<T> first();
	public Optional<RNode<T>> firstOpt();
	public Stream<RNode<T>> all();
}
