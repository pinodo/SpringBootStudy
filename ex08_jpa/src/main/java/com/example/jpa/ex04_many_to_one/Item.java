package com.example.jpa.ex04_many_to_one;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 자식 엔티티

@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String itemName;

  // 연관 관계 핵심 부분 (다대일 연관 관계 - 단방향)
  // FetchType.EAGER: (즉시 로딩) find() 호출 시 연관 관계를 가진 테이블을 함께 조회하는 방식 (디폴트)
  // FetchType.LAZY: (지연 로딩) find() 호출 시 자신만 조회하고(연관 관계의 데이터는 프록시 객체로 처리; HibernateProxy), 연관 관계의 테이블은 나중에 실제로 필요할 때 조회하는 방식 [실무 표준]
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_item_to_category"))
  private Category category;

  public Item(String itemName, Category category) {
    this.itemName = itemName;
    // 부모 엔티티를 영속화해야 자식 엔티티를 영속화할 수 있다
    this.category = category;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", itemName=" + itemName + ", category=" + category + "]";
  }
}
