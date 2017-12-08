package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class IntercomRequestReply extends CodeInfo {

	private String interlocutor;
	private boolean action;
	
	public IntercomRequestReply(int pid,String username,boolean status,int reason) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setStatus(status);
		setReason(reason);
	}
	public IntercomRequestReply(int pid,String username,String interlocutor,boolean action) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setInterlocutor(interlocutor);
		setAction(action);
	}
	
	public String getInterlocutor() {
		return interlocutor;
	}
	public void setInterlocutor(String interlocutor) {
		this.interlocutor = interlocutor;
	}

	public boolean isAction() {
		return action;
	}
	public void setAction(boolean action) {
		this.action = action;
	}

}
