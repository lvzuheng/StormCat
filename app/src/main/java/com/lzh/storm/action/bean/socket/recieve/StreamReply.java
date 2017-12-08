package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class StreamReply extends CodeInfo {

	private String interlocutor;
	private byte[] stream;
	
	public StreamReply() {
		// TODO Auto-generated constructor stub
	}
	public StreamReply(int pid,String username,String interlocutor,byte[] stream) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setUsername(username);
		setInterlocutor(interlocutor);
		setStream(stream);
	}
	
	public String getInterlocutor() {
		return interlocutor;
	}
	public void setInterlocutor(String interlocutor) {
		this.interlocutor = interlocutor;
	}
	public byte[] getStream() {
		return stream;
	}
	public void setStream(byte[] stream) {
		this.stream = stream;
	}
}
