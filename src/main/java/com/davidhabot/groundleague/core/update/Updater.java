package com.davidhabot.groundleague.core.update;

import lombok.Getter;
import lombok.NonNull;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Updater implements Runnable{
    Logger logger = Logger.getLogger(this.getClass());
    private final String name;
    @NonNull @Getter
    private final AtomicBoolean isUpdating;
    @NonNull //업데이트 가능한 모든 클래스를 Updatable 인터페이스로 저장한 리스트
    private static final List<Updatable> updatables = new ArrayList<>();//추가나 제거도 되지만, 그보단 get 이 많이쓰이므로(For each) ArrayList 를 채택한다.
    private long updateCount; //총 업데이트 횟수를 저장하는 정수형 변수

    public Updater(String name) {
        this.name = name;
        this.isUpdating = new AtomicBoolean(false);
        updateCount = 0;
        logger.log(Level.TRACE, "업데이터 생성 - " + this.toString());
    }

    public static void addUpdatable(Updatable updatable) {
        updatables.add(updatable);
    }

    //renderables 에서 idx 를 통해 요소를 제거한다.
    public void removeUpdatable(Updatable updatable) {
        updatables.remove(updatable);
    }
    //renderables 에서 idx 를 통해 요소를 제거한다.
    public void removeUpdatable(int idx) {
        updatables.remove(idx);
    }

    //updatables 에 있는 Updatable 클래스를 모두 업데이팅한다.
    public void updateAll() {
        for(Updatable updatable : updatables) {
            updatable.update(); //updatables 에 있는 Updatable 을 하나씩 updatable 에 담아서 업데이트한다.
        }
    }

    //쓰레드를 종료시킨다.
    public synchronized void stopUpdate() {
        isUpdating.set(false);
        logger.log(Level.INFO, "THREAD_STOP - Updating" + "(Update Count) : " + updateCount);
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "THREAD_START - Updating");
        isUpdating.set(true);
        while (isUpdating.get()) {
            updateAll();
            updateCount++;
        }
    }

    @Override
    public String toString() {
        return name + " 업데이터";
    }
}
