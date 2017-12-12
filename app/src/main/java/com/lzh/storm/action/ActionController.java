package com.lzh.storm.action;

import android.util.Log;

import com.lzh.storm.action.bean.CodeInfo;
import com.lzh.storm.action.code.Protocol;
import com.lzh.storm.module.socket.Channel;
import com.lzh.storm.utils.json.JsonCoder;
import com.lzh.storm.view.activity.ControllerActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lzh on 2017/12/7.
 */

public class ActionController {


    private static ActionController actionController;
    private ControllerActivity controllerActivity;
    private Map<String,Protocol> protocolMap ;

    private ActionController() {

    }

//    public static ActionController getInsatance(ControllerActivity controllerActivity) {
//        if (actionController == null) {
//            actionController = new ActionController();
//        }
//        return actionController.setControllerActivity(controllerActivity);
//    }
    public static ActionController getInsatance() {
        if (actionController == null) {
            actionController = new ActionController();
        }
        return actionController;
    }

    public ControllerActivity getControllerActivity() {
        return controllerActivity;
    }

    public ActionController setControllerActivity(ControllerActivity controllerActivity) {
        this.controllerActivity = controllerActivity;
        if(protocolMap == null){
            protocolMap = GainCode.getProtocol(this.controllerActivity.getBaseContext());
        }
        return this;
    }

    public void excute(String msg,Channel channel){
        CodeInfo codeInfo = JsonCoder.parseJson(msg,CodeInfo.class);
        Protocol protocol = protocolMap.get(String.valueOf(codeInfo.getPid()));
        if(protocol!=null){
            protocol.excute(msg,getControllerActivity(),channel);
        }

    }

}
