package com.example.PlantsCafe.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter //세터가 없으면 dto 생성이 안됨..
public class MemberDto {
    private Long id;
    private String userId;
    private String userPassword;
    private String nickName;
}
