package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.boss.Boss;

public interface iMinigame {
    boolean play();
    // it used to be FinalBoss
    boolean play(ServiceMember usr);
}
