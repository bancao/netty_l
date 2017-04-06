package com.structure.trees.avl;

public enum BalanceFactor {
	LH("左子树高"), EH("左右等高"), RH("右子树高");

	private String illustration = "";

	private BalanceFactor(String s) {
		this.illustration = s;
	}

	public String toString() {
		return this.illustration;
	}
}
