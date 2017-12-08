package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class GroupInfosReply extends CodeInfo {

	private int action;
	private String member;
	
	
	public GroupInfosReply() {
		// TODO Auto-generated constructor stub
	}
	public GroupInfosReply(Integer pid,String username,String member,int action) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setMember(member);
		setAction(action);
	}
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	
}
