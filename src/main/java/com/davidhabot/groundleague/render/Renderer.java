package com.davidhabot.groundleague.render;

import com.davidhabot.groundleague.render.graphics.ScreenController;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 렌더링 가능한 (Renderable) 모든 클래스들의 render 관련 메소드를 관장하는 클래스이다.
 * ScreenController 에 렌더링 메소드를 통해 렌더러블 오브젝트들을 레이어링한다.
 * @see #Renderer(ScreenController) 해당 클래스의 생성자이다.
 *
 * @author DavidHabot
 * @since 1.0-SNAPSHOT
 */
public class Renderer implements Runnable{
    @NonNull @Getter
    private AtomicBoolean isRendering; //현재 렌더링중인지 여부
    @NonNull
    private final List<Renderable> renderables; //렌더링 가능한 모든 클래스를 Renderable 인터페이스로 저장한 리스트
    @NonNull @Getter //헨더링할 스크린
    private final ScreenController screen;
    private long renderCount;

    //렌더러의 기본 생성자이다. 렌더링할 스크린을 인자로 받는다.
    public Renderer(ScreenController screenController) {
        isRendering = new AtomicBoolean(false);
        renderables = new ArrayList<>();
        screen = screenController;
        renderCount = 0;
    }

    //렌더링 가능한 클래스를 renderables 리스트에 추가한다.
    public void addRenderable(Renderable renderable) {
        if(renderable instanceof PriorityRenderable) { //만약 renderable 이 우선순위 Renderable(PriorityRenderable) 일경우,
            int priority = ((PriorityRenderable) renderable).getPriority(); //renderable 을 PriorityRenderable 로 다운캐스팅하여 중요도를 가져온다.
            //우선순위를 비교해 더 큰 값을 가지고있는 요소를 더 먼저 렌더링한다
            for(int i = 0; i < renderables.size(); i++) {
                Renderable element = renderables.get(i); //renderables 의 Renderable 를 하나하나 가져온다.
                if(element instanceof PriorityRenderable) { //만약 PriorityRenderable 로 형변환이 가능할경우,
                    if(((PriorityRenderable)element).getPriority() < priority) { //해당 요소와 추가할 renderable 의 우선순위를 비교한다.
                        renderables.add(i, renderable); //만약 추가한 renderable 의 우선순위가 더 클경우 원래 요소가 있던 자리에 renderable 를 놓고, 나머지 요소를 한칸씩 밀어낸다.
                        break; //추가작업이 완료되었으므로, for문을 빠져나간다.
                    }
                }else {
                    int elementPriority = 0;//만약 해당 요소가 PriorityRenderable 로 형변환이 불가능할경우, 해당 요소의 중요도를 0으로 가정한다.
                    if(elementPriority < priority) { //해당 요소와 추가할 renderable 의 우선순위를 비교한다.
                        renderables.add(i, renderable); //만약 추가한 renderable 의 우선순위가 더 클경우 원래 요소가 있던 자리에 renderable 를 놓고, 나머지 요소를 한칸씩 밀어낸다.
                        break; //추가작업이 완료되었으므로, for문을 빠져나간다.
                    }else if (i == renderables.size()-1) //만약 마지막 요소까지 비교했을때 우선순위가 가장 낮을경우(무조건적으로 우선순위는 음수가 된다.)
                        renderables.add(renderable); //리스트의 가장 마지막에 renderable 를 추가한다.
                }
            }
            System.out.println("중요도가 " + priority + "인 Renderable 을 추가하였습니다."); //해당 클래스가 정상적으로 추가되었을경우 성공로그를 출력한다.
        }else //만약 renderable(추가할 요소) 가 PriorityRenderable 로 형변환이 불가능할경우, renderables 리스트의 맨 끝에 renderable 을 추가한다.
            renderables.add(renderable);
    }

    //renderables 에서 요소를 제거한다.
    public void removeRenderable(Renderable renderable) {
        renderables.remove(renderable);
    }

    //renderables 에 있는 Renderable 클래스를 모두 screen 에 렌더링한다.
    public void renderAll() {
        for(Renderable renderable : renderables)
            renderable.render(screen);
    }

    //렌더러 쓰레드를 종료시킨다.
    public synchronized void stopRender() {
        isRendering.set(false);
        System.out.println("랜더링을 종료합니다" + "\n총 랜더링 횟수 : " + renderCount + "회");
    }

    //렌더러 쓰레드의 run 메소드이다. 실제 렌더링 작업을 담당한다.
    @Override
    public void run() {
        isRendering.set(true); //렌더링을 시작하므로 isRendering 을 true 로 변환시킨다.
        while (isRendering.get()) {
            renderAll();
            renderCount++;
        }
    }

    //toString 을 오버라이딩하여 어떠한 스크린의 랜더러인지 표시한다.
    @Override
    public String toString() {
        return screen.toString() + "의 랜더러";
    }
}