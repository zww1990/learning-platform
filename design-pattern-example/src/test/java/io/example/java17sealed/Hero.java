package io.example.java17sealed;

/**
 * 英雄基类
 * 密封的抽象类
 */
public abstract sealed class Hero permits TankHero, AttackHero, SupportHero {
}
