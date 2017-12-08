package com.lzh.storm.action.bean.socket.recieve;

import com.lzh.storm.action.bean.CodeInfo;

import java.util.List;


public class GroupListReply extends CodeInfo {
	private List<Group> groups;
	
	public GroupListReply(int pid,String username,List<Group> groups) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setGroups(groups);
	}

	public static class Group{
		private int groupId;
		private String groupName;
		private int memberNum;
		private boolean status;
	
		public Group(int groupId,String groupName,int memberNum,boolean status) {
			// TODO Auto-generated constructor stub
			setGroupId(groupId);
			setGroupName(groupName);
			setMemberNum(memberNum);
			setStatus(status);
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
		public int getMemberNum() {
			return memberNum;
		}
		public void setMemberNum(int memberNum) {
			this.memberNum = memberNum;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
	}



	public List<Group> getGroups() {
		return groups;
	}



	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
