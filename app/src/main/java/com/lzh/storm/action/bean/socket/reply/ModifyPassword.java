package com.lzh.storm.action.bean.socket.reply;


import com.lzh.storm.action.bean.CodeInfo;

public class ModifyPassword extends CodeInfo {
	private String password;
	private String newPassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
