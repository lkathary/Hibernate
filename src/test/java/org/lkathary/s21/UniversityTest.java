package org.lkathary.s21;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.lkathary.s21.entity.*;
import org.lkathary.s21.util.HibernateUtil;

import javax.persistence.Query;


@Slf4j
class UniversityTest {

    @Test
    public void testAddNewStudentAndCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");

        Course course = Course.builder().name("Five").build();

        Student student = Student.builder().firstname("LEXX").lastname("Sokolov").age(22).build();

        course.setStudents(student);
        session.saveOrUpdate(course);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testFindStudentByCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");

        Course course = session.get(Course.class, 4L);
        System.out.println("==== " + course);
        course.getStudents().forEach(System.out::println);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testFindStudentByNameCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");


        Query query = session.createQuery("from Course where name = :name");
        query.setParameter("name", "Four");
        Course course = (Course) query.getSingleResult();

        System.out.println("==== " + course);
        course.getStudents().forEach(System.out::println);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testOrhpalRemovalStudent() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");


        Student student = session.get(Student.class, 3L);
        System.out.println("==== " + student);

        session.delete(student);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testOneToOne() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");

        Student student = Student.builder().firstname("AXXX").lastname("Spite").age(25).build();

        Progress progress = Progress.builder().grade(8).build();

        progress.setStudent(student);
        session.saveOrUpdate(progress);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testDeleteStudentByGrade() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");


        Query query = session.createQuery("from Course where name = :name");
        query.setParameter("name", "Four");
        Course course = (Course) query.getSingleResult();

        System.out.println("==== " + course);
        course.getStudents().forEach(System.out::println);

        course.getStudents().removeIf(student -> student.getProgress().getGrade() < 6);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testAddStudentToExistCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");


        Query query = session.createQuery("from Course where name = :name");
        query.setParameter("name", "Four");
        Course course = (Course) query.getSingleResult();

        Student student = Student.builder().firstname("Genna").lastname("Dron").age(22).build();
        Progress progress = Progress.builder().grade(8).build();

        progress.setStudent(student);
        course.setStudents(student);

        session.save(course);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }

    @Test
    public void testManyToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        log.info("Begin transaction");

        Trainer trainer = Trainer.builder().name("Dyatlov").build();

        TrainerCourse trainerCourse = TrainerCourse.builder().duration(55).build();

        trainerCourse.setTrainer(trainer);

        trainerCourse.setCourse(session.get(Course.class, 1L));
        session.save(trainerCourse);

        TrainerCourse trainerCourse1 = TrainerCourse.builder().duration(44).build();

        trainerCourse1.setTrainer(session.get(Trainer.class, 4L));
        trainerCourse1.setCourse(session.get(Course.class, 2L));
        session.save(trainerCourse1);

        session.getTransaction().commit();
        log.debug("Transaction is committed");
    }
}

