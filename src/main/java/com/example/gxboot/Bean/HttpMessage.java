package com.example.gxboot.Bean;

import java.io.Serializable;

/**
 * ClassName: HttpMessage<br/>
 * Description: 消息实体类<br/>
 * date 2019/3/25 16:42
 *
 * @author gx
 * @Version 1.0
 * @since 1.7
 */
public class HttpMessage implements Serializable {
	private static final long serialVersionUID = 5252675368063349199L;

	/**
	 * 消息类型
	 */
	private String messageType;

	/**
	 * 消息内容
	 */
	private String messageContent;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
}
