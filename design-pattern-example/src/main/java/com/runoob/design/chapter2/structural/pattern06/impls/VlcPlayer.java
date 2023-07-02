package com.runoob.design.chapter2.structural.pattern06.impls;

import com.runoob.design.chapter2.structural.pattern06.AdvancedMediaPlayer;

/**
 * vlc播放器
 */
public class VlcPlayer implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		//什么也不做
	}

}
