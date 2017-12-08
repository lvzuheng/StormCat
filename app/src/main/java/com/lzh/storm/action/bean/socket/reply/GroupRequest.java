package com.lzh.storm.action.bean.socket.reply;


import com.lzh.storm.action.bean.CodeInfo;

public class GroupRequest extends CodeInfo {

	private int groupId;
	private String groupName;
	private int action;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
}
