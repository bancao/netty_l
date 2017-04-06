package com.structure.trees.avl;

public class AVL<E extends Comparable<E>> {

    /**树根*/  
    private AVLNode<E> root=null;  
    /**当前树是否变高*/  
    public boolean isTaller=false; 
    
    public AVL(){  
    }  
    
    public boolean insert(E key) {
    	if(key == null) return false;
    	
    	AVLNode<E> node = new AVLNode<E>(key);
    	if(root == null) {
    		root = node;
    		return true;
    	}
    	return insertAVL(key, root);
    	
    }

	private boolean insertAVL(E key, AVLNode<E> node) {
		if(node.getKey().equals(key)) {
			return false;
		}
		
		if(node.getKey().compareTo(key) >0 ){
			if(node.getLchild() == null){
				AVLNode<E> newNode=new AVLNode<E>(key);  
                node.lchild=newNode; //设置左孩子结点  
                newNode.parent=node; //设置父亲结点  
                isTaller=true; //树长高了  
			} else {
				insertAVL(key, node.getLchild());
			}
			if (isTaller) {
				switch(node.getbFactor()) {
				case LH: 
					leftBalance(node);
					isTaller = false;
					break;
				case RH:
					node.bFactor = BalanceFactor.EH;
					isTaller = false;
					break;
				case EH:
					node.bFactor = BalanceFactor.LH;
					isTaller = true;
					break;
				}
			}
			
			
		} else {
			if(node.getRchild() == null) {
				System.out.println("].  插入到"+node.key+"的右孩子");  
                AVLNode<E> newNode=new AVLNode<E>(key);  
                node.rchild=newNode; //设置右孩子结点  
                newNode.parent=node; //设置父亲结点  
                isTaller=true; //树长高了
			} else {
				insertAVL(key, node.getRchild());
			}
			
			if(isTaller) {
				switch(node.getbFactor()) {
				case LH:
					node.bFactor = BalanceFactor.EH;
					isTaller = false;
					break;
				case EH:
					node.bFactor = BalanceFactor.RH;
					isTaller = true;
					break;
				case RH:
					rightBalance(node);
					isTaller =false;
					break;
				}
			}
		}
		
		return false;
	}

	private void rightBalance(AVLNode<E> node) {
		
	}

	private void leftBalance(AVLNode<E> node) {
		AVLNode<E> lc=node.getLchild();
		switch (lc.getbFactor()) {
		case LH:
			node.bFactor = lc.bFactor = BalanceFactor.EH;
			rRotate(node);
			break;
		case RH:
			
		}
		
	}

	private void rRotate(AVLNode<E> node) {
		AVLNode<E> lc= node.lchild;//lc指向node的左孩子结点 
		
		if(node.parent == null) {
			root = lc;
		} else if(node.parent.lchild.key.compareTo(node.key)==0) {
			node.parent.lchild = lc;
		}  else  {
			node.parent.rchild = lc;  
		}
		lc.parent = node.parent;
		
		node.parent = lc;
	}
	
	private void lRotate(AVLNode<E> node) {
		AVLNode<E> rc= node.rchild;//lc指向node的左孩子结点
		if(node.parent == null) {
			root = rc;
		} else if(node.parent.rchild.key.compareTo(node.key)==0) {
			node.parent.lchild = rc;
		}  else  {
			node.parent.rchild = rc;  
		}
	}
}
