package com.response;

import java.util.List;
import com.model.User;

public class CodeMessageList {
	private int Code;
	private String Message;
	private List<User> Object;
	
	public CodeMessageList(int code, String message, List<User> object) {
		super();
		Code = code;
		Message = message;
		Object = object;
	}
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public List<User> getObject() {
		return Object;
	}
	public void setObject(List<User> object) {
		Object = object;
	}
}
