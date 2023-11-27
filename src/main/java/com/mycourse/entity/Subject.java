package com.mycourse.entity;

import jakarta.persistence.*;
import lombok.Getter;
@Getter
@Entity
public class Subject {
    @Id
    private Integer id;
    private Integer grade;
    private Integer sem;
    private String category;
    private Integer subnum;
    private String subname;
    private Integer score;
    private Boolean ai;
    private Boolean cs;
    private Boolean coding;
    private Boolean teach;
}