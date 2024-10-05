package cl.facitest.controllers;

import cl.facitest.controllers.response.BaseResponse;
import cl.facitest.controllers.response.ExamResponse;
import cl.facitest.enums.ExamResponseCodes;
import cl.facitest.exceptions.test.ExamAlreadyExistsException;
import cl.facitest.exceptions.test.ExamNotFoundException;
import cl.facitest.models.Exam;
import cl.facitest.services.test.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ExamController {

    private IExamService iExamService;
    @Autowired
    public void setiTestService(IExamService iExamService){this.iExamService = iExamService;}
    @PostMapping
    public ResponseEntity<BaseResponse> createTest(@Validated @RequestBody Exam exam) {
        try {
            iExamService.createTest(exam);
            return ResponseEntity.status(ExamResponseCodes.TEST_CREATED.getHttpStatus())
                    .body(BaseResponse.builder()
                            .code(ExamResponseCodes.TEST_CREATED.getCode())
                            .message(ExamResponseCodes.TEST_CREATED.getMessage())
                            .build());
        } catch (ExamAlreadyExistsException e) {
            return ResponseEntity.status(ExamResponseCodes.TEST_ALREADY_EXISTS.getHttpStatus())
                    .body(BaseResponse.builder()
                            .code(ExamResponseCodes.TEST_ALREADY_EXISTS.getCode())
                            .message(ExamResponseCodes.TEST_ALREADY_EXISTS.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(ExamResponseCodes.INTERNAL_SERVER_ERROR.getHttpStatus())
                    .body(BaseResponse.builder()
                            .code(ExamResponseCodes.INTERNAL_SERVER_ERROR.getCode())
                            .message(ExamResponseCodes.INTERNAL_SERVER_ERROR.getMessage())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getTestById(@Validated @PathVariable Long id) {
        try {
            Exam exam = iExamService.getTestById(id);
            return ResponseEntity.status(ExamResponseCodes.TEST_CREATED.getHttpStatus())
                    .body(ExamResponse.builder()
                            .code(ExamResponseCodes.TEST_CREATED.getCode())
                            .message(ExamResponseCodes.TEST_CREATED.getMessage())
                            .file(exam.getFile())
                            .build());
        } catch (ExamNotFoundException e) {
            return ResponseEntity.status(ExamResponseCodes.TEST_NOT_FOUND.getHttpStatus())
                    .body(ExamResponse.builder()
                            .code(ExamResponseCodes.TEST_NOT_FOUND.getCode())
                            .message(ExamResponseCodes.TEST_NOT_FOUND.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(ExamResponseCodes.INTERNAL_SERVER_ERROR.getHttpStatus())
                    .body(ExamResponse.builder()
                            .code(ExamResponseCodes.INTERNAL_SERVER_ERROR.getCode())
                            .message(ExamResponseCodes.INTERNAL_SERVER_ERROR.getMessage())
                            .build());
        }
    }
}
