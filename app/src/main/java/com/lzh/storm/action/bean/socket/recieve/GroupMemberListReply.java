package com.lzh.storm.action.bean.socket.recieve;

import com.lzh.storm.action.bean.CodeInfo;

import java.util.List;


public class GroupMemberListReply extends CodeInfo {
	
	private int groupId;
	private String groupName;
	private List<User> users;
	private boolean status =true;
	private int reason;
	
	public GroupMemberListReply(int pid,String username,boolean status,int reason) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setStatus(status);
		setReason(reason);
	}
	public GroupMemberListReply(int pid,String username,int groupId,String groupName,List<User> users) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setGroupId(groupId);
		setGroupName(groupName);
		setUsers(users);
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



	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}



	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public static class User{
		private String username;
		private boolean online;
		private boolean status;
		
		public User(String username,boolean online,boolean status) {
			// TODO Auto-generated constructor stub
			setUsername(username);
			setOnline(online);
			setStatus(status);
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public boolean isOnline() {
			return online;
		}
		public void setOnline(boolean online) {
			this.online = online;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}
	}
}
