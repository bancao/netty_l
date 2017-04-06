package com.structure.trees;

import java.util.ArrayList;
import java.util.List;

public class BSTree<E extends Comparable<E>> {
	private BSNode<E> root = null;

	public BSTree() {
	}
	
	public boolean insert(E value) {
		if(root == null) {
			root = new BSNode<E>(value);
			return true;
		} 
		return insertBST(root, value);
	}

	private boolean insertBST(BSNode<E> root, E value) {
		if(root.getValue().compareTo(value) == 0) {
			return false;
		}
		if(root.getValue().compareTo(value) > 0) {
			if(root.getLeft() == null) {
				BSNode<E> node = new BSNode<E>(value);
				node.setParent(root);
				root.setLeft(node);
				return true;
			} 
			return insertBST(root.getLeft(), value);
		}

		if(root.getRight() == null) {
			BSNode<E> node = new BSNode<E>(value);
			node.setParent(root);
			root.setRight(node);
			return true;
		}
		return insertBST(root.getRight(), value);
	}
	
	public void beforeOrderTraverse() {
		if(root == null) {
			return;
		}
		List<BSNode<E>> queue = new ArrayList<BSNode<E>>();
		BSNode<E> node = root;
		queue.add(node);
		
		while(!queue.isEmpty()) {
			while(node.getLeft() != null) {
				node = node.getLeft();
				queue.add(node);
			}
			if(!queue.isEmpty()) {
				BSNode<E> firstNode = queue.get(0);
				System.out.println(firstNode.getValue());
				queue.remove(0);
				if(null != firstNode.getRight()) {
					node = firstNode.getRight();
					queue.add(node);
				}
				
			}
		}
	}
	
	
	public void ceforeOrderTraverse(BSNode<E> node) {
		if(node == null) {
			return;
		}
		System.out.println(node.getValue());
		ceforeOrderTraverse(node.getLeft());
		ceforeOrderTraverse(node.getRight());
		
	}
	
	public void afterOrderTraverse() {
		if(root == null) {
			return;
		}
		
		
	}
	
	
	public void midOrderTraverse(){
		if(root == null) {
			return;
		}
		List<BSNode<E>> traverseStack = new ArrayList<BSNode<E>>();
		BSNode<E> node = root;
		traverseStack.add(root);
		while(!traverseStack.isEmpty()) {
			while(null != node.getLeft()) {
				node = node.getLeft();
				traverseStack.add(node);
			}
			
			if(!traverseStack.isEmpty()) {
				BSNode<E> topNode =  traverseStack.get(traverseStack.size()-1);
				System.out.println(topNode.getValue());
				traverseStack.remove(traverseStack.size()-1);
				if(null != topNode.getRight()){
					node = topNode.getRight();
					traverseStack.add(node);
				}
			}
		}
		
	}

	
	public static void main(String[] args) {
		BSTree<Integer> bst = new BSTree<Integer>();
		bst.insert(1);
		bst.insert(9);
		bst.insert(8);
		bst.insert(44);
		bst.insert(33);
		bst.insert(22);
		bst.insert(0);
//		bst.midOrderTraverse();
		bst.beforeOrderTraverse();
		bst.ceforeOrderTraverse(bst.root);
	}

}

class BSNode<E extends Comparable<E>> {
	private E value;
	private BSNode<E> left;
	private BSNode<E> right;
	private BSNode<E> parent;
	
	
	public BSNode(E value) {
		this.value = value;
	}
	public E getValue() {
		return value;
	}
	public void setValue(E value) {
		this.value = value;
	}
	public BSNode<E> getLeft() {
		return left;
	}
	public void setLeft(BSNode<E> left) {
		this.left = left;
	}
	public BSNode<E> getRight() {
		return right;
	}
	public void setRight(BSNode<E> right) {
		this.right = right;
	}
	public BSNode<E> getParent() {
		return parent;
	}
	public void setParent(BSNode<E> parent) {
		this.parent = parent;
	}
}
