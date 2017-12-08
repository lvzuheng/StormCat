package com.lzh.storm.action.bean.socket.reply;


import com.lzh.storm.action.bean.CodeInfo;

public class IntercomRequest extends CodeInfo {

	private boolean action;

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

}
