package org.lijiao.cms.model;

import java.util.List;

public class NewChannel {
	private Channel parent;
	private List<Channel> child;
	public Channel getParent() {
		return parent;
	}
	public void setParent(Channel parent) {
		this.parent = parent;
	}
	public List<Channel> getChild() {
		return child;
	}
	public void setChild(List<Channel> child) {
		this.child = child;
	}
	@Override
	public String toString() {
		return "NewChannel [parent=" + parent + ", child=" + child + "]";
	}
	
}
