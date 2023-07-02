package com.runoob.design.chapter2.structural.pattern6.impls;

import com.runoob.design.chapter2.structural.pattern6.AdvancedMediaPlayer;

/**
 * mp4播放器
 */
public class Mp4Player implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		//什么也不做
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: " + fileName);
	}

}
