package com.yuan.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuan.exception.StatusCodeException;

/**
 * JSON返回结果。
 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = -8963262177114836103L;

	protected T data;

	protected int status;

	protected String msg;

	@JsonCreator
	protected JsonResult(@JsonProperty("status") int status, @JsonProperty("msg") String msg, @JsonProperty("data") T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static <T> JsonResult<T> create(int status, String message, T data) {
		return new JsonResult(status, message, data);
	}

//	public static <T> JsonResult<T> create(RpcResult<T> rpcResult) {
//		Status statusCode = rpcResult.getStatusCode();
//		return create(statusCode.getStatus(), statusCode.getReason(), rpcResult.getData());
//	}

	public static <T> JsonResult<T> create(Status statusCode, T data) {
		return create(statusCode.getStatus(), statusCode.getReason(), data);
	}

	public static <T> JsonResult<T> success(T data) {
		Status statusCode = Status.success();
		return create(statusCode, data);
	}

	public static <T> JsonResult<T> error(String message) {
		Status statusCode = Status.error(message);
		return create(statusCode, null);
	}

	public static <T> JsonResult<T> error(int status, String message) {
		Status statusCode = Status.error(status, message);
		return create(statusCode, null);
	}

	public static <T> JsonResult<T> error(StatusCodeException e) {
		Status statusCode = e.getStatusCode();
		return create(statusCode, null);
	}

	public int getStatus() {
		return status;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return Status.create(status, msg).isSuccess();
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JsonResult)) return false;

		JsonResult<?> that = (JsonResult<?>) o;

		if (status != that.status) return false;
		if (data != null ? !data.equals(that.data) : that.data != null) return false;
		return msg != null ? msg.equals(that.msg) : that.msg == null;
	}

	@Override
	public int hashCode() {
		int result = data != null ? data.hashCode() : 0;
		result = 31 * result + status;
		result = 31 * result + (msg != null ? msg.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "JsonResult{" +
				"data=" + data +
				", status=" + status +
				", msg='" + msg + '\'' +
				'}';
	}
}
