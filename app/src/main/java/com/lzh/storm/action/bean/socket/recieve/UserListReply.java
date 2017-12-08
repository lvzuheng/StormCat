package com.lzh.storm.action.bean.socket.recieve;

import com.lzh.storm.action.bean.CodeInfo;

import java.util.List;


public class UserListReply extends CodeInfo {

	private List<User> users;
	public UserListReply(int pid,String username, List<User> users) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setUsers(users);
	}
	
	public UserListReply() {
		// TODO Auto-generated constructor stub
	}
	

	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static class User{
		private String username;
		private int groupId;
		private String groupName;
		private boolean online;
		
		public User(String username,int groupId,String groupName,boolean online) {
			// TODO Auto-generated constructor stub
			setUsername(username);
			setGroupId(groupId);
			setGroupName(groupName);
			setOnline(online);
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
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
		public boolean isOnline() {
			return online;
		}
		public void setOnline(boolean online) {
			this.online = online;
		}
	}
}

