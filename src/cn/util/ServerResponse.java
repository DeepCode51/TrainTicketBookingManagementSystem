package cn.util;

import java.util.List;

public class ServerResponse<T> {
	private String code;
	private String msg;
	private Integer count;
	private List<T> data;

	public ServerResponse() {

	}
	
	public ServerResponse(String code) {
		this.code = code;
	}

	public ServerResponse(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ServerResponse(String code, List<T> data) {
		super();
		this.code = code;
		this.data = data;
	}

	public ServerResponse(String code, Integer count, List<T> data) {
		super();
		this.code = code;
		this.count = count;
		this.data = data;
	}

	public ServerResponse(String code, String msg, Integer count, List<T> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
