package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class UserInfoReply extends CodeInfo {

	private boolean intercomstatus;
	private String groupName;
	private int groupId;
	private String user;
	
	public UserInfoReply() {
		// TODO Auto-generated constructor stub
	}
	public UserInfoReply(int pid,String username,String user,int groupId,String groupName,boolean intercomstatus) {
		// TODO Auto-generated constructor stub
		setUsername(username);
		setPid(pid);
		setIntercomstatus(intercomstatus);
		setGroupId(groupId);
		setGroupName(groupName);
		setUser(user);
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public boolean isIntercomstatus() {
		return intercomstatus;
	}
	public void setIntercomstatus(boolean intercomstatus) {
		this.intercomstatus = intercomstatus;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
