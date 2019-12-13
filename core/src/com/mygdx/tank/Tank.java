package com.mygdx.tank;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bonus.BonusManager;
import com.mygdx.bonus.TankBonus;
import com.mygdx.enumeration.TankType;
import com.mygdx.game.AbstractGameObject;
import com.mygdx.game.Constants;
import com.mygdx.wall.WallManager;

public class Tank extends AbstractGameObject {

    // 描述坦克是否还存活
    private boolean isAlive;
    // 描述坦克的种类
    private TankType tankType;
    // 坦克的移动速度
    private int moveSpeed;
    // 坦克的射速
    private int shootSpeed;
    //
    public static TankManager tankManager;

    public Tank() {
        super();
        this.isAlive = true;
        this.moveSpeed = Constants.DEFAULT_MOVE_SPEED;
        this.shootSpeed = Constants.DEFAULT_SHOOT_SPEED;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    // 根据类别得到一个坦克的实例
    public static Tank getInstance(TankType tankType) {
        Tank tank = null;
        // TODO
        return null;
    }

    public boolean changeStatus(int mode) {
        return false;
    }


    public TankType getTankType() {
        return this.tankType;
    }

    public void setTankType(TankType tankType) {
        this.tankType = tankType;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public int getShootSpeed() {
        return shootSpeed;
    }

    public void setShootSpeed(int shootSpeed) {
        this.shootSpeed = shootSpeed;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }


    //碰撞检测
    @Override
    public boolean checkCrash()
    {
        boolean isCrash = false;
        if(WallManager.checkCrash(this))
        {
            isCrash = true;
            //和墙碰撞
        }
        if(BonusManager.checkCrash(this))
        {
            isCrash = true;
            //和buff碰撞
        }
        if(TankManager.checkCrash(this))
        {
            isCrash = true;
            //和坦克碰撞
        }
        if(TankManager.checkCrash(this))
        {
            isCrash = true;
            //和子弹碰撞
        }
        return isCrash;
    }
}
