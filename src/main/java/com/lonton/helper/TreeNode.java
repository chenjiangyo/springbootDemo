package com.lonton.helper;

import lombok.Data;

import java.util.List;

@Data
public abstract class TreeNode implements Comparable {

	private String value;    //节点的代码
	
	private String title;    //节点的值
	
	private String nodeParentId; //父节点的代码
	
	private List<TreeNode> children;

    public abstract String getNodeParentId();
    
    public abstract String getValue();
    
    public abstract String getTitle();

    public abstract boolean isRoot();

}
