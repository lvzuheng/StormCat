package com.lzh.storm.action.bean.socket.recieve;

public class OfflineReply {
	private String offlineUser;
	private String offlineClient;
	private int orgId;
	
	
	public OfflineReply(String offlineUser,String offlineClient,int orgId) {
		// TODO Auto-generated constructor stub
		setOfflineUser(offlineUser);
		setOfflineClient(offlineClient);
		setOrgId(orgId);
	}
	public OfflineReply() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getOfflineUser() {
		return offlineUser;
	}
	public void setOfflineUser(String offlineUser) {
		this.offlineUser = offlineUser;
	}
	public String getOfflineClient() {
		return offlineClient;
	}
	public void setOfflineClient(String offlineClient) {
		this.offlineClient = offlineClient;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
}
