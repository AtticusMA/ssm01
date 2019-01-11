package com.qiusheng.www.common;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 标识编码
	 */
	private int code;
	/**
	 * 传递的信息
	 */
	private String message;
	/**
	 * 传递的数据
	 */
	private Object data;
	
	public Result(){
		super();
	}
	
	public Result(int code,String message){
		super();
		this.code=code;
		this.message = message;
	}
	
	public Result(int code,String message,Object data){
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
