package com.davidhabot.groundleague.core.actor.pawn;

import com.davidhabot.groundleague.core.action.AdvancedMovable;
import com.davidhabot.groundleague.core.action.Rotatable;
import com.davidhabot.groundleague.core.actor.Actor;
import com.davidhabot.groundleague.core.actor.Direction;
import lombok.Getter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//Pawn 은 움직일 수 있고, 회전할 수 있으므로, Movable 과 Rotatable 을 상속받는다.
public class Pawn extends Actor implements AdvancedMovable, Rotatable {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Getter
    protected Direction dir; //해당 Pawn 의 방향
    @Getter
    protected double speed; //해당 Pawn 의 이동속도
    protected double xWeight, yWeight; //해당 Pawn 이 움직여야할 좌표 가중치를 저장한다. (일정한 속도로 움직이기 위함)

    @Override
    public void move(Direction dir) {
        move(dir.getXWeight(), dir.getYWeight());
    }

    @Override //인자로 받은 x, y 가중치만큼 가중치를 누적한다.
    public void move(int xWeight, int yWeight) {
        this.xWeight += xWeight; //x가중치 누적
        this.yWeight += yWeight; //y가중치 누적
    }

    @Override //인자로 받은 방향으로 Pawn 의 방향을 수정한다.
    public void rotate(Direction dir) {
        this.dir = dir; //Pawn 의 방향을 인자로 받은 방향으로 변환
    }

    @Override //업데이트 주기마다 실행되는 로직
    public void update() {
        moveLogic(); //이동로직
    }
    
    public void moveLogic() {
        int dirX = getDir(xWeight), dirY = getDir(yWeight); //이동할 x, y 로의 방향을 getDir 메서드를 통해 초기화한다.

        double distance = getDistance();
        double distanceModifier = 1; //움직이는 방향이 대각선이 될 경우를 대비한 보정치
        /*x, y 둘다 움직일경우, 대각선이 되므로, 피타고라스의 공식을 통해 대각선으로 1을 움직이는 보정치를 곱한다
        a^2 + b^2 = c^2 (a = b, c = 1)
        a^2 + a^2 = 1^2
        2 * (a^2) = 1
        그러므로, a^2 = 1/2
        그러므로, a = root(1/2)
        root(1/2)
        = root(1) / root(2) = 1 / root(2) = root(2) / root(2)^2 = root(2) / 2
        = 1.4142... / 2 = 0.7071
         */
        if(xWeight != 0 && yWeight != 0) {
            distanceModifier = 0.7071;
        }

        if(xWeight != 0) { //만약 x가중치가 남아있을경우
            moveX((distance * distanceModifier) * dirX);
        }
        if(yWeight != 0) { //만약 y가중치가 남아있을경우
            moveY((distance * distanceModifier) * dirY);
        }
    }
    
    //좌표 가중치에 대한 해당 좌표로의 방향을 구하는 메서드
    public int getDir(double weight) {
        int dirX = 1; //반환할 해당 좌표로의 방향을 1(오른족 or 아래) 로 초기화한다.
        if(weight < 0) //만약 좌표 가중치가 음수일경우 (즉, 해당 좌표 로의 방향이 왼쪽일경우)
            dirX = -1; //해당 좌표로의 방향을 음수(왼쪽 or 위)으로 초기화한다.
        return dirX; //해당 좌표로의 방향을 반환한다.
    }

    //플레이어의 update 주기마다의 이동거리를 구한다.
    public double getDistance() {
        return speed; //주기마다의 이동거리를 계산해서 반환한다.
    }

    public void moveX(double x) {
        this.x += x; //해당 Pawn 의 x값을 Pawn 의 이동거리 공식 만큼 dirX 방향으로 수정한다.
        xWeight -= x; //이후, x가중치에서 해당 이동거리만큼을 뺀다.
        logger.log(Level.TRACE, "Pawn 의 X좌표를 " + this.x + "에서 " + x + "만큼 이동하였습니다");
    }
    public void moveY(double y) {
        this.y += y; //해당 Pawn 의 y값을 Pawn 의 이동거리 공식만큼 dirY 방향으로 수정한다.
        yWeight -= y; //이후, y가중치에서 해당 이동거리만큼을 뺀다.
        logger.log(Level.TRACE, "Pawn 의 Y좌표를 " + this.x + "에서 " + x + "만큼 이동하였습니다");
    }
}
