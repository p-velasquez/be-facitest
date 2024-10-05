package cl.facitest.services.test;

import cl.facitest.exceptions.test.ExamAlreadyExistsException;
import cl.facitest.exceptions.test.ExamNotFoundException;
import cl.facitest.models.Exam;
import cl.facitest.repository.ExamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements IExamService {

    private ExamRepository examRepository;
    @Autowired
    public void setTestRepository(ExamRepository examRepository){this.examRepository = examRepository;}
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamService.class);

    @Override
    public List<Exam> getTests() {
        try {
            return (List<Exam>) examRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Exam getTestById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Test with ID " + id + " not found."));
    }

    @Override
    public void createTest(Exam exam) {
        try {
            LOGGER.info("Creating a new test: {}", exam);
            List<Exam> exams = getTests();
            boolean testExists = exams.stream()
                    .anyMatch(existingExam -> existingExam.getId().equals(exam.getId())
                            || existingExam.getName().equals(exam.getName()));

            if (testExists) {
                LOGGER.warn("Test already exists: {}", exam);
                throw new ExamAlreadyExistsException("Test with ID " + exam.getId() + " already exists.");
            }

            examRepository.save(exam);
        } catch (Exception e) {
            LOGGER.error("Error while creating test: {}", exam.getName(), e);
            throw new RuntimeException("Error saving test", e);
        }
    }
}
