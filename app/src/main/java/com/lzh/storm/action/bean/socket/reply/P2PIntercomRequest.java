package com.lzh.storm.action.bean.socket.reply;


import com.lzh.storm.action.bean.CodeInfo;

public class P2PIntercomRequest extends CodeInfo {

	private boolean action;
	private String receiver;
	
	public P2PIntercomRequest(int pid,String username,String receiver,boolean action) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setAction(action);
		setReceiver(receiver);
	}
	
	public P2PIntercomRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}
}
