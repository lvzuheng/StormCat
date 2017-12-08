package com.lzh.storm.action;

import com.lzh.storm.action.bean.CodeInfo;
import com.lzh.storm.action.code.Protocol;
import com.lzh.storm.module.socket.Channel;
import com.lzh.storm.utils.json.JsonCoder;
import com.lzh.storm.view.activity.ControllerActivity;

/**
 * Created by lzh on 2017/12/7.
 */

public class ActionController {


    private static ActionController actionController;
    private ControllerActivity controllerActivity;

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
        return this;
    }

    public void excute(String msg,Channel channel){
        CodeInfo codeInfo = JsonCoder.parseJson(msg,CodeInfo.class);
        Protocol protocol = (Protocol) GainCode.FromCode(getControllerActivity().getBaseContext(),codeInfo.getPid()+"");
        protocol.excute(msg,getControllerActivity(),channel);
    }

}
