package com.runoob.design.chapter2.structural.pattern6;

import com.runoob.design.chapter2.structural.pattern6.impls.Mp4Player;
import com.runoob.design.chapter2.structural.pattern6.impls.VlcPlayer;

/**
 * 创建实现了 MediaPlayer 接口的适配器类。
 */
public class MediaAdapter implements MediaPlayer {
	AdvancedMediaPlayer advancedMusicPlayer;

	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer = new VlcPlayer();
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer = new Mp4Player();
		}
	}

	@Override
	public void play(String audioType, String fileName) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer.playMp4(fileName);
		}
	}

}
