package cl.facitest.services.test;

import cl.facitest.models.Exam;

import java.util.List;

public interface IExamService {
    List<Exam> getTests();
    void createTest(Exam exam);

    Exam getTestById(Long id);
}
