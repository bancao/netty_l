package com.structure.trees.rb;

public class RBNode<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	E value;
	RBNode<E> left;
	RBNode<E> right;
	RBNode<E> parent;
	boolean color = BLACK;

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public RBNode<E> getLeft() {
		return left;
	}

	public void setLeft(RBNode<E> left) {
		this.left = left;
	}

	public RBNode<E> getRight() {
		return right;
	}

	public void setRight(RBNode<E> right) {
		this.right = right;
	}

	public RBNode<E> getParent() {
		return parent;
	}

	public void setParent(RBNode<E> parent) {
		this.parent = parent;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

}
