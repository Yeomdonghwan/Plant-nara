package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String nickName;

    public static MemberEntity createMemberEntity(MemberDto memberDto) {
        return new MemberEntity(
                null,
                memberDto.getUserId(),
                memberDto.getUserPassword(),
                memberDto.getNickName()
        );
    }
}
