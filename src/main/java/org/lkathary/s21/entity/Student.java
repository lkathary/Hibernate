package org.lkathary.s21.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"course", "progress"})
@Builder
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Progress progress;

}
