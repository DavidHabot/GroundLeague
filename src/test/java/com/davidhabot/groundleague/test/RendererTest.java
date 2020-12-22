package com.davidhabot.groundleague.test;

import com.davidhabot.groundleague.core.render.screen.ScreenController;
import com.davidhabot.groundleague.core.render.PriorityRenderable;
import com.davidhabot.groundleague.core.render.Renderer;
import org.apache.log4j.Level;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RendererTest implements Testable {
    @Test //렌더러가 잘 생성되는지 검사한다
    public void testRendererCreation() {
        //테스트의 로직이 실행되지 전에 RenderCreation 테스트의 시작 메세지를 로거에 띄운다.
        logger.log(Level.INFO, "TEST_START - RenderCreation");
        Renderer rendererA = new Renderer(new ScreenController("#Test")); //첫번째 렌더러를 생성한다.
        assertNotNull("renderer 가 Null 입니다", rendererA);
        //렌더러가 정상적으로 생성되었을경우 렌더러 생성 메세지를 로거에 띄운다.
        logger.log(Level.DEBUG, rendererA.toString() + " 생성 완료");
        //모든 로직이 끝나면 RenderCreation 테스트의 종료 메세지를 로거에 띄운다.
        logger.log(Level.INFO, "TEST_END - RenderCreation");
    }

    @Test //랜더러의 생성주기 (start, run, stop 등) 을 검사한다
    public void testRendererPriority() {
        //테스트 ID 5, 7, 6, 4, 1, 2, 3, 8 순으로 실행되면 성공
        logger.log(Level.INFO, "TEST_START - RenderCreation");
        Renderer renderer = new Renderer(new ScreenController("#Test"));
        Renderer.addRenderable((screen) -> logger.log(Level.TRACE, "렌더링 테스트 1"));
        Renderer.addRenderable((screen) -> logger.log(Level.TRACE, "렌더링 테스트 2"));
        Renderer.addRenderable((screen) -> logger.log(Level.TRACE, "렌더링 테스트 3"));
        PriorityRenderable renderableA = new PriorityRenderable(1) {
            @Override
            public void render(ScreenController screen) {
                logger.log(Level.TRACE, "렌더링 테스트 4 (우선순위 = 1)");
            }
        };
        PriorityRenderable renderableB = new PriorityRenderable(3) {
            @Override
            public void render(ScreenController screen) {
                logger.log(Level.TRACE, "렌더링 테스트 5 (우선순위 = 3)");
            }
        };
        PriorityRenderable renderableC = new PriorityRenderable(2) {
            @Override
            public void render(ScreenController screen) {
                logger.log(Level.TRACE, "렌더링 테스트 6 (우선순위 = 2)");
            }
        };
        PriorityRenderable renderableD = new PriorityRenderable(3) {
            @Override
            public void render(ScreenController screen) {
                logger.log(Level.TRACE, "렌더링 테스트 7 (우선순위 = 3)");
            }
        };
        PriorityRenderable renderableE = new PriorityRenderable(-1) {
            @Override
            public void render(ScreenController screen) {
                logger.log(Level.TRACE, "렌더링 테스트 8 (우선순위 = -1)");
            }
        };
        Renderer.addRenderable(renderableA);
        Renderer.addRenderable(renderableB);
        Renderer.addRenderable(renderableC);
        Renderer.addRenderable(renderableD);
        Renderer.addRenderable(renderableE);
        Renderer.addRenderable((screen) -> logger.log(Level.TRACE, "렌더링 테스트 9"));

        Thread thread = new Thread(renderer, "renderer");
        thread.start();
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() < time + 10); //메인쓰레드가 아니므로 Thread.sleep(10)을 대체하기 위함 (0.01초동안 대기)
        renderer.stopRender();
        //모든 로직이 끝나면 RenderPriority 테스트의 종료 메세지를 로거에 띄운다.
        logger.log(Level.INFO, "TEST_END - RenderPriority");
    }

    @Test //renderer 의 주 로직인 renderAll 의 정상적 작동여부와 성능을 테스팅한다.
    public void testRender() {
    }
}
