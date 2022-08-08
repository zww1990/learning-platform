package com.example.hello.service.ws;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * WebSocketServer
 * 
 * @author zhang weiwei
 * @since 2022年8月8日,下午1:33:43
 */
@Service
@ServerEndpoint("/websocket/{sid}")
@Slf4j
public class WebSocketServer {
	/** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。 */
	private static int onlineCount = 0;
	/** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
	/** 与某个客户端的连接会话，需要通过它来给客户端发送数据 */
	private Session session;
	/** 接收sid */
	private String sid = "";

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 * @param sid
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.session = session;
		this.sid = sid;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		log.info("有新窗口开始监听:{}, 当前在线人数为:{}", sid, getOnlineCount());
//		try {
//			sendMessage("连接成功");
//		} catch (IOException e) {
//			log.error(e.getLocalizedMessage(), e);
//		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		log.info("有一连接关闭！当前在线人数为:{}", getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message 客户端发送过来的消息
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口{}的信息:{}", sid, message);
		// 群发消息
		for (WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	@OnError
	public void onError(Session session, Throwable ex) {
		log.error(ex.getLocalizedMessage(), ex);
	}

	/**
	 * 实现服务器主动推送
	 * 
	 * @param message
	 * @throws IOException
	 * @since 2022年8月8日,下午2:20:41
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 * 
	 * @param message
	 * @param sid
	 * @throws IOException
	 * @since 2022年8月8日,下午2:21:05
	 */
	public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
		log.info("推送消息到窗口:{}，推送内容:{}", sid, message);
		for (WebSocketServer item : webSocketSet) {
			try {
				// 这里可以设定只推送给这个sid的，为null则全部推送
				if (sid == null) {
					item.sendMessage(message);
				} else if (item.sid.equals(sid)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}
