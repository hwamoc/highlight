package com.liner.example.user.domain;

import com.liner.example.common.BaseTimeEntity;
import com.liner.example.theme.domain.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String Email;

    private String password;

    @OneToOne
    @JoinColumn(name = "themeId")
    private Theme theme;
}
