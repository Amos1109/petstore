package com.petstore.util;

import java.util.HashMap;

public class JsonResult {
	private Boolean success;
	private String msg;
	private String msg2;
	private String msg3;
	private HashMap<String,Object>data;

	public void addData(String key,Object value){
		if(data==null){
			data=new HashMap<>();
		}
		data.put(key,value);
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public String getMsg3() {
		return msg3;
	}
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
}
