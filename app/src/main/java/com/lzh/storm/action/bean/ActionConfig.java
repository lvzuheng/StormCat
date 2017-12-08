package com.lzh.storm.action.bean;

public class ActionConfig {
	
/***********状态鉴别类************************************************/	
	/**身份鉴别*/
	public static String TERMINAL_USER = "T";
	public static String CLIENT_USER = "C";
	
	//应答回复
	public static int ACTION_ANSWER = 0;
	public static int ACTION_ERROR =  1;
	
	/**state状态*/
	public static int SECUSS = 0;
	public static int FAIL = 1;
	
	/**state状态*/
	public static boolean RESPONSE_SECUSS = true;
	public static boolean RESPONSE_FAIL = false;
	/**reson状态*/
	public static int NOEXCEPTION  = 0;
	public static int DATAEXCEPTION =  1;
	public static int IDEXCEPTION =  2;
	public static int CODEEXCEPTION =  3;
	public static int PDEXCEPTION =  4;
	public static int NOGROUPNOEXCEPTION =  5;
	public static int NOPROMIISIONEXCEPTION =  6;
/*****************************************************************/	
	
/***********协议类************************************************/
	/**修改用户名答复*/
	public static int MODIFYNICKNAME =  7004;
	
	/**修改用户名答复*/
	public static int MODIFYPASSWORD =  7005;
	
	/**用户列表*/
	public static int USERLIST =  7006;
			
	/**心跳包设置*/
	//心跳包协议号设置
	public static int HEARTACTION = 8000;

	/**登录请求*/
	public static int LOGIN_REQUEST= 6001;
	/**登录回复*/
	public static int LOGIN_ANSWER = 7001;
	
	/**在线状态变更*/
	public static int ONOFFLINEINFOS= 7002;
	
	/**对讲组列表回复*/
	public static int GROUPLIST = 7007;
	
	/**进入对讲组回复*/
	public static int GROUPREQUEST = 7008;
	
	/**组内成员列表申请*/
	public static int GROUPMEMBERLIST = 7009;
	
	/**对讲消息发送*/
	public static int GROUPINFOS = 7009;
	
	/**对讲申请*/
	public static int INTERCOMREQUEST = 7010;
	
	/**对讲申请*/
	public static int STREAMREPLY = 7011;

	/**点对点对讲申请*/
	public static int P2PINTERCOMREQUEST = 7012;
	
	/**点对点对讲申请回复*/
	public static int P2PINTERCOMREPLY = 7013;
	/**点对点对讲流传输*/
	public static int P2PSTREAM = 7014;
	
/*****************************************************************/	
	
	
/*********************特殊应答类**************************************/
	/**未进组*/
	public static int NOTINGROUP = -1;
	/**进组*/
	public static final int INGROUP = 1;
	/**出组*/
	public static final int OUTGROUP = 0;
	/**申请/同意打开对讲*/
	public static boolean OPENINTERCOM = true;
	/**申请/同意关闭对讲*/
	public static boolean CLOSEINTERCOM = false;
	
/*****************************************************************/	
}
