package com.mygdx.wall;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.enumeration.WallType;
import com.mygdx.game.AbstractGameObject;
import com.mygdx.game.Constants;
import org.w3c.dom.Text;

public abstract class Wall extends AbstractGameObject {

    // 静态墙管理器实例
    public static WallManager wallManager = new WallManager();
    // 墙的类型
    private WallType wallType;
    // 墙的生命值
    private int life;

    public Wall() {
        super();
        this.setSize(Constants.WALL_SIZE, Constants.WALL_SIZE);
        this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
    }

    public Wall(Texture texture) {
        super(texture);
        this.setSize(Constants.WALL_SIZE, Constants.WALL_SIZE);
        this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
    }

    /**
     * 碰撞检测函数
     *
     * @return true表示和其他物体碰撞，false表示没有碰撞
     */
    @Override
    public boolean checkCrash() {
        return super.checkCrash();
    }

    void beenAttacked(int hit)
    {
        this.life -= hit;
    }

    static Pixmap createProceduralPixmap(int width, int height)
    {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
//        OrthographicCamera camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
//        camera.position.set(0, 0, 0);
//        camera.update();
//        spriteBatch.setProjectionMatrix(camera.combined);
//        spriteBatch.begin();
//        SpriteBatch batch = new SpriteBatch();
//        batch.begin();
//        spriteBatch.draw(this.getTexture(), this.getX() - 50, this.getY() - 50, this.getTexture().getWidth(), this.getTexture().getHeight());
        this.draw(spriteBatch);
//        batch.end();
//        spriteBatch.draw(this.getTexture(), this.getTexture().getWidth(), this.getTexture().getHeight());
//        spriteBatch.end();

    }


    public static Wall getInstance(WallType wallType){
        // TODO 得到实例类
        return null;
    }

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
