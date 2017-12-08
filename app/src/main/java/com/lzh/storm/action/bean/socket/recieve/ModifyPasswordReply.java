package com.lzh.storm.action.bean.socket.recieve;


import com.lzh.storm.action.bean.CodeInfo;

public class ModifyPasswordReply extends CodeInfo {
		
		public ModifyPasswordReply(int pid,String username,boolean status , int reason) {
			// TODO Auto-generated constructor stub
			setPid(pid);
			setUsername(username);
			setReason(reason);
			setStatus(status);
		}
		public ModifyPasswordReply() {
			// TODO Auto-generated constructor stub
		}
		
}
