package org.lkathary.s21.entity;

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
@Table(name = "trainer_course")
public class TrainerCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer duration;

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getTrainerCourses().add(this);
    }

}
