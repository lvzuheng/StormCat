package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class ModifyUserNameReply extends CodeInfo {
	private String newUserName;
	
	public ModifyUserNameReply(Integer pid , String username,boolean status,int reason,String newUserName) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(newUserName);
		setStatus(status);
		setReason(reason);
		setNewUserName(newUserName);
	}
	public ModifyUserNameReply() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getNewUserName() {
		return newUserName;
	}
	public ModifyUserNameReply setNewUserName(String newUserName) {
		this.newUserName = newUserName;
		return this;
	}
}
