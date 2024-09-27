package org.lkathary.s21;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lkathary.s21.entity.Course;
import org.lkathary.s21.entity.Student;
import org.lkathary.s21.util.HibernateUtil;

@Slf4j
public class University {


    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        try (SessionFactory sessionFactory = HibernateUtil.builSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            log.info("Begin transaction");

            Course course = Course.builder()
                    .name("Five")
                    .build();

            Student student = Student.builder()
                    .firstname("Ivan")
                    .lastname("Sokolov")
                    .age(22)
                    .build();

            course.setStudents(student);
            session.saveOrUpdate(course);

            session.getTransaction().commit();
            log.info("Commit transaction");

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
}