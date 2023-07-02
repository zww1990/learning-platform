package com.runoob.design.chapter2.structural.pattern06;

/**
 * 适配器模式（Adapter Pattern）
 */
public class AdapterPatternDemo {
	/**
	 * 使用 AudioPlayer 来播放不同类型的音频格式。
	 */
	public static void main(String[] args) {
		MediaPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}
