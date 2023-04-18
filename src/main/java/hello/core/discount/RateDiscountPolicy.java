package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DisCountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) { //cmd + shift + t : 테스트 생성 단축키
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
