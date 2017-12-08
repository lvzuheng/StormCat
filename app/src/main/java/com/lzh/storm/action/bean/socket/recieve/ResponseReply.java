package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class ResponseReply extends CodeInfo {

	private String message;
	
	public ResponseReply(int pid,String username,boolean status,int reason,String message){
		setPid(pid);
		setUsername(username);
		setStatus(status);
		setReason(reason);
		setMessage(message);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
