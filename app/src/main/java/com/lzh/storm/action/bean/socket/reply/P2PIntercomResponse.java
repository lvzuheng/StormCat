package com.lzh.storm.action.bean.socket.reply;


import com.lzh.storm.action.bean.CodeInfo;

public class P2PIntercomResponse extends CodeInfo {

	private String applicant;
	private boolean answer;
	
	public P2PIntercomResponse(int pid,String username,String applicant,boolean answer) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setApplicant(applicant);
		setAnswer(answer);
	}
	public P2PIntercomResponse() {
		// TODO Auto-generated constructor stub

	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public boolean isAnswer() {
		return answer;
	}
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
}
