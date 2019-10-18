package com.lonton.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree< T extends TreeNode> {
	
	private List<T> root = new ArrayList<T>();

	public List<T> getRoot() {
		return root;
	}
	
	//双循环成树
	public void buildTree(List<T> list){
		for(T child : list) {
			//找到根
			if(child.isRoot()) {
				this.getRoot().add(child);
			}
			//找到子节点
			for(T node : list ) {
				if(child.getValue().equals(node.getNodeParentId())) {
					if(child.getChildren() == null) {
						child.setChildren(new ArrayList<>());
					}
					child.getChildren().add(node);
				}
			}
		}		
	}

	public void sortTree(List<T> treeNodeList){
		Collections.sort(treeNodeList);
		for(T treeNode : treeNodeList){
			if(treeNode.getChildren() != null){
				sortTree((List<T>)treeNode.getChildren());
			}
		}
	}
	
}
