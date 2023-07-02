package com.runoob.design.chapter3.behavioral.pattern18;

import java.time.LocalDateTime;

/**
 * 聊天室
 */
public class ChatRoom {
	public static void showMessage(User user, String message) {
		System.out.println(LocalDateTime.now() + " [" + user.getName()
				+ "] : " + message);
	}
}
