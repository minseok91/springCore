package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 호출할 때마다 생성되는지 확인
        MemberService memberService1 = appConfig.memberService();
        // 2. 호출할 때마다 생성되는지 확인
        MemberService memberService2 = appConfig.memberService();

        // 참조값 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 !== memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    private MemberService getService(AppConfig appConfig) {
        return appConfig.memberService();
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
        // same == 비교
        // equals : equals 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 호출할 때마다 생성되는지 확인
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 2. 호출할 때마다 생성되는지 확인
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        // 참조값 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // memberService1 !== memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
