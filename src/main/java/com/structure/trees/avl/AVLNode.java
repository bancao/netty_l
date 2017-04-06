package com.structure.trees.avl;

public class AVLNode<E extends Comparable<E>> {
	E key = null;
	BalanceFactor bFactor = BalanceFactor.EH;
    AVLNode<E> parent=null;  
    AVLNode<E> lchild,rchild=null;  
      
    AVLNode(E k){  
        this.key=k;  
    }  
    
    public String toString(){  
        String lchildStr=(this.lchild==null)?"null":this.lchild.key.toString();  
        String rchildStr=(this.rchild==null)?"null":this.rchild.key.toString();  
        return this.key+"[lchild="+lchildStr+"，rchild="+rchildStr+"]";  
    }

	public E getKey() {
		return key;
	}

	public void setKey(E key) {
		this.key = key;
	}

	public BalanceFactor getbFactor() {
		return bFactor;
	}

	public void setbFactor(BalanceFactor bFactor) {
		this.bFactor = bFactor;
	}

	public AVLNode<E> getParent() {
		return parent;
	}

	public void setParent(AVLNode<E> parent) {
		this.parent = parent;
	}

	public AVLNode<E> getLchild() {
		return lchild;
	}

	public void setLchild(AVLNode<E> lchild) {
		this.lchild = lchild;
	}

	public AVLNode<E> getRchild() {
		return rchild;
	}

	public void setRchild(AVLNode<E> rchild) {
		this.rchild = rchild;
	} 
}
