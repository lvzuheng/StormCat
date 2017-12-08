package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class P2PIntercomRequestReply extends CodeInfo {

	private String applicant;
	private boolean action;
	
	public P2PIntercomRequestReply(int pid,String username,String applicant,boolean action) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setApplicant(applicant);
		setAction(action);
	}
	public P2PIntercomRequestReply() {
		// TODO Auto-generated constructor stub

	}

	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public boolean isAction() {
		return action;
	}
	public void setAction(boolean action) {
		this.action = action;
	}
}
