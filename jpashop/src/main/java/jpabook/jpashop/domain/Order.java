package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;
    private LocalDateTime

}
