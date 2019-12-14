package com.mygdx.tank;

import com.mygdx.bonus.Bonus;
import com.mygdx.enumeration.BonusType;
import com.mygdx.enumeration.ObjectType;
import com.mygdx.enumeration.TankType;
import com.mygdx.game.Constants;

import java.util.Random;

public class EnemyTank extends Tank {

    // 敌方坦克管理器，负责注册，查找，删除现有的敌方坦克
    public static TankManager enemyTankManager = new TankManager(Constants.ENEMYTANK_MANAGER);
    // 描述该敌方坦克是否携带有bonus
    private boolean hasBonus;
    // 描述敌方坦克是否被冰冻住
    private boolean isFrozen;

    public EnemyTank(boolean hasBonus, TankType tankType) {
        super(tankType);
        this.setType(ObjectType.ENEMYTANK);
        this.hasBonus = hasBonus;
        this.setDirect(Constants.DIRECT.SOUTH);
    }

    @Override
    public boolean changeStatus(int mode) {
        switch (mode) {
            case Constants.DEAD:
                this.setAlive(false);
                break;
            case Constants.FREEZE:
                this.isFrozen = true;
                break;
            case Constants.UNFREEZE:
                this.isFrozen = false;
                break;
            default:
                return false;
        }
        return true;
    }

    public void move() {
        //System.out.println(""+this.isNorth()+this.isSouth()+this.isWest()+this.isEast());
        Constants.DIRECT direct = this.getDirect();
        if (this.getDirect().equals(Constants.DIRECT.EAST) && this.isEast()) {
            direct = Constants.DIRECT.EAST;
        } else if (this.getDirect().equals(Constants.DIRECT.WEST) && this.isWest()) {
            direct = Constants.DIRECT.WEST;
        } else if (this.getDirect().equals(Constants.DIRECT.NORTH) && this.isNorth()) {
            direct = Constants.DIRECT.NORTH;
        } else if (this.getDirect().equals(Constants.DIRECT.SOUTH) && this.isSouth()) {
            direct = Constants.DIRECT.SOUTH;
        } else {
            Random r = new Random();
            int rInt = r.nextInt(100);
            {
                int west = 1;
                int north = 1;
                int south = 1;
                int east = 1;

                if ((HeroTank.heroTankManager.getTanks().get(0).getX() - this.getX()) > Constants.TANK_SIZE/2)
                    east++;
                else if ((HeroTank.heroTankManager.getTanks().get(0).getX() - this.getX()) < -Constants.TANK_SIZE/2)
                    west++;

                if ((HeroTank.heroTankManager.getTanks().get(0).getY() - this.getY()) > Constants.TANK_SIZE/2)
                    north++;
                else if ((HeroTank.heroTankManager.getTanks().get(0).getY() - this.getY()) < -Constants.TANK_SIZE/2)
                    south++;

                if ((-0.5 - this.getX()) > Constants.TANK_SIZE)
                    east++;
                else if ((-0.5 - this.getX()) < -Constants.TANK_SIZE)
                    west++;

                if ((-6.5 - this.getY()) > Constants.TANK_SIZE)
                    east++;
                else if ((-6.5 - this.getY()) < -Constants.TANK_SIZE)
                    west++;

                int sum = east + west + south + north;
                System.out.println(""+east+" "+west+" "+north+" "+south);
                if ((float) rInt / 100 < (float) north / sum && this.isNorth()) {
                    direct = Constants.DIRECT.NORTH;
                } else if ((float) rInt / 100 < (float) (north + east) / sum && this.isEast()) {
                    direct = Constants.DIRECT.EAST;
                } else if ((float) rInt / 100 < (float) (north + east + south) / sum && this.isSouth()) {
                    direct = Constants.DIRECT.SOUTH;
                } else// if ((float) rInt / 100 <= 1 && this.isWest())
                {
                    direct = Constants.DIRECT.WEST;
                }
            }
        }
        move(direct);
        if(this.checkCrash())
        {
            Random r = new Random();
            int rInt = r.nextInt(100);
            {
                int west = 1;
                int north = 1;
                int south = 1;
                int east = 1;

                if ((HeroTank.heroTankManager.getTanks().get(0).getX() - this.getX()) > Constants.TANK_SIZE / 2)
                    east++;
                else if ((HeroTank.heroTankManager.getTanks().get(0).getX() - this.getX()) < -Constants.TANK_SIZE / 2)
                    west++;

                if ((HeroTank.heroTankManager.getTanks().get(0).getY() - this.getY()) > Constants.TANK_SIZE / 2)
                    north++;
                else if ((HeroTank.heroTankManager.getTanks().get(0).getY() - this.getY()) < -Constants.TANK_SIZE / 2)
                    south++;

                if ((-0.5 - this.getX()) > Constants.TANK_SIZE)
                    east++;
                else if ((-0.5 - this.getX()) < -Constants.TANK_SIZE)
                    west++;

                if ((-6.5 - this.getY()) > Constants.TANK_SIZE)
                    east++;
                else if ((-6.5 - this.getY()) < -Constants.TANK_SIZE)
                    west++;

                int sum = east + west + south + north;
                System.out.println("" + east + " " + west + " " + north + " " + south);
                if ((float) rInt / 100 < (float) north / sum && this.isNorth()) {
                    direct = Constants.DIRECT.NORTH;
                } else if ((float) rInt / 100 < (float) (north + east) / sum && this.isEast()) {
                    direct = Constants.DIRECT.EAST;
                } else if ((float) rInt / 100 < (float) (north + east + south) / sum && this.isSouth()) {
                    direct = Constants.DIRECT.SOUTH;
                } else// if ((float) rInt / 100 <= 1 && this.isWest())
                {
                    direct = Constants.DIRECT.WEST;
                }
                this.setDirect(direct);
            }

        }

    }



    //坦克移动
    public void move(Constants.DIRECT direct) {
        this.setDirect(direct);
        //改变图片
        switch (direct) {
            case SOUTH:
                if (this.isSouth()) {
                    this.translate(0, -this.getMoveSpeed());
                    this.setNorth(true);
                    this.setEast(true);
                    this.setWest(true);
                }
                break;
            case NORTH:
                if (this.isNorth()) {
                    this.translate(0, this.getMoveSpeed());
                    this.setSouth(true);
                    this.setWest(true);
                    this.setEast(true);
                    ;
                }
                break;
            case WEST:
                if (this.isWest()) {
                    this.translate(-this.getMoveSpeed(), 0);
                    this.setEast(true);
                    this.setNorth(true);
                    ;this.setSouth(true);
                }
                break;
            case EAST:
                if (this.isEast()) {
                    this.translate(this.getMoveSpeed(), 0);
                    this.setWest(true);
                    this.setNorth(true);
                    this.setSouth(true);
                }
                break;
        }
    }

    @Override
    //停车调整方向
    public void blockForward() {
        //调头
        switch (this.getDirect()) {
            case NORTH:
                this.setDirect(Constants.DIRECT.SOUTH);
                break;
            case SOUTH:
                this.setDirect(Constants.DIRECT.NORTH);
                break;
            case WEST:
                this.setDirect(Constants.DIRECT.EAST);
                break;
            case EAST:
                this.setDirect(Constants.DIRECT.WEST);
                break;
        }

        this.moveTank(this.getDirect());

        switch (this.getDirect()) {
            case NORTH:
                this.setDirect(Constants.DIRECT.SOUTH);
                break;
            case SOUTH:
                this.setDirect(Constants.DIRECT.NORTH);
                break;
            case WEST:
                this.setDirect(Constants.DIRECT.EAST);
                break;
            case EAST:
                this.setDirect(Constants.DIRECT.WEST);
                break;
        }

    }

    private Constants.DIRECT int2direct(int direct)
    {
        switch (direct)
        {
            case 1:
                return Constants.DIRECT.NORTH;
            case 2:
                return Constants.DIRECT.SOUTH;
            case 3:
                return Constants.DIRECT.WEST;
            case 4:
                return Constants.DIRECT.EAST;
        }
        return  Constants.DIRECT.NORTH;
    }

    @Override
    public void beenAttacked() {
        super.beenAttacked();
        if (hasBonus) {
            Random random = new Random();
            BonusType bonusType = BonusType.getInstance(random.nextInt(6));
            float randomX = random.nextFloat();
            float randomY = random.nextFloat();
            Bonus bonus = Bonus.bonusManager.createBonus(bonusType, randomX, randomY);
        }
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public void setHasBonus(boolean hasBonus) {
        this.hasBonus = hasBonus;
    }


}
