package com.lzh.storm.action.bean.socket.recieve;

import com.lzh.storm.action.bean.CodeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LoginReply extends CodeInfo {
	private String nickname;
	private String dateTime;
	
	public LoginReply() {
		// TODO Auto-generated constructor stub
		setDateTime();
	}
	
	public LoginReply(int pid,String username,String nickname){
		setPid(pid);
		setUsername(username);
		setNickname(nickname);
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public LoginReply setDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		this.dateTime = sdf.format(new Date());
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
