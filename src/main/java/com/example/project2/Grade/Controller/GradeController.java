package com.example.project2.Grade.Controller;

import com.example.project2.Grade.Repository.GradeRepository;
import com.example.project2.Grade.Service.Impl.GradeSeviceImpl;
import com.example.project2.Json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    GradeSeviceImpl gradeSevice;

    @Autowired
    GradeRepository gradeRepository;

    @GetMapping("/student/{student_id}")
    public ResponseEntity<?> getGradeStudentById(@PathVariable(name = "student_id") Integer id) {
        return Optional.ofNullable(gradeRepository.findByStudentId(id))
                .map(rslist -> !rslist.isEmpty() ? JsonResult.found(rslist, null) : JsonResult.notFound(rslist))
                .orElse(JsonResult.serverError("Intenal Server Error"));
    }

    @GetMapping("/student/page/{student_id}")
    public ResponseEntity<?> getGradeStudentByPage(@PathVariable("student_id") Integer studentId,
                                                   @RequestParam Integer page, @RequestParam Integer pageSize) {
         try {
             Pageable pageable = PageRequest.of(page, pageSize);
             int total = gradeRepository.findByStudentId(studentId).size();
             return Optional.ofNullable(gradeSevice.getGradeByPage(studentId, pageable))
                     .map(rsPage -> !rsPage.isEmpty() ? JsonResult.found(rsPage.getContent(), total) : JsonResult.notFound("Page"))
                     .orElse(JsonResult.serverError("Intenal Server Error"));
    //        return ResponseEntity.ok (gradeSevice.getGradeByPage(studentId, pageable).getContent());
         } catch (Exception ex) {
             return (JsonResult.serverError("Intenal Server Error"));
         }
    }
    @GetMapping("/student/test")
    public ResponseEntity<?> getGradeStudentById() {
        Integer s = gradeRepository.findByStudentIdtest();
        Map<String, Integer> ss = new HashMap<>();
        ss.put("message", s);
        return ResponseEntity.ok(ss);
    }

//    @GetMapping("/student/search")
//    public ResponseEntity<?>
}
