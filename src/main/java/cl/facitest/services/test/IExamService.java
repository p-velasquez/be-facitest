package cl.facitest.services.test;

import cl.facitest.models.Exam;

import java.util.List;

public interface IExamService {
    List<Exam> getExams();
    void createExam(Exam exam);

    Exam getExamById(Long id);
}
