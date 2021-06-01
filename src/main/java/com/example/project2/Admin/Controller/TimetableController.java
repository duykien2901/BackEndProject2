package com.example.project2.Admin.Controller;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Request.TimetableReq;
import com.example.project2.Admin.Model.Request.TimetableSearchReq;
import com.example.project2.Admin.Repository.TimeTableRepository;
import com.example.project2.Admin.Service.Impl.TimetableServiceImpl;
import com.example.project2.Json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class TimetableController {
    @Autowired
    TimetableServiceImpl timetableService;

    @Autowired
    TimeTableRepository timeTableRepository;

    @GetMapping("/api/timetable/all")
    public ResponseEntity<?> getAllClassroom() {
        return ResponseEntity.ok(timetableService.findAll());
    }

    @PutMapping("/api/timetable/change/{id}")
    public ResponseEntity<?> insertTimetable(@PathVariable Integer id, @RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.insertTimetable(id, timetableReq));
    }


    @GetMapping("/api/timetable/page")
    public ResponseEntity<?> getTimetableByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {

        try {
            Pageable pageable = PageRequest.of(page, pageSize);

            return Optional.ofNullable(timetableService.getAllByPage(pageable))
                    .map(rsPage -> !rsPage.isEmpty() ? JsonResult.found(rsPage, timeTableRepository.findAll().size()) :
                            JsonResult.notFound("Page"))
                    .orElse(JsonResult.serverError("Intenal Server Error"));
            //        return ResponseEntity.ok (gradeSevice.getGradeByPage(studentId, pageable).getContent());
        } catch (Exception ex) {
            return (JsonResult.serverError("Intenal Server Error"));
        }
    }

    @GetMapping("/api/timetable/search")
    public ResponseEntity<?> searchTimetableByPage(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                   @RequestParam(defaultValue = "") String value) {
        try {
            Pageable pageable = PageRequest.of(page, pageSize);

            return Optional.ofNullable(timetableService.searchTimetableByPage(value, pageable))
                    .map(rsPage -> !rsPage.isEmpty() ? JsonResult.found(rsPage, timeTableRepository.findByClassroomNameNotPage(value).size()) :
                            JsonResult.notFound("Page"))
                    .orElse(JsonResult.serverError("Intenal Server Error"));
        } catch (Exception e) {
            return (JsonResult.serverError("Intenal Server Error"));
        }
    }

    @PostMapping("/api/timetable/search/details")
    public ResponseEntity<?> searchTimetableByPageDetails(@RequestParam Integer page, @RequestParam Integer pageSize,
                                                          @RequestBody TimetableSearchReq timetableSearchReq)
                                                          {
        try {
            Pageable pageable = PageRequest.of(page, pageSize);

            return Optional.ofNullable(timetableService.searchTimetableDetailsByPage(timetableSearchReq.getClassName(),
                    timetableSearchReq.getTeacherName(), timetableSearchReq.getCourseName(), pageable))
                    .map(rsPage -> !rsPage.isEmpty() ? JsonResult.found(rsPage,
                            timeTableRepository.findByAllDetailNotPage(timetableSearchReq.getClassName(),
                                    timetableSearchReq.getTeacherName(),  timetableSearchReq.getCourseName()).size()) :
                            JsonResult.notFound("Page"))
                    .orElse(JsonResult.serverError("Intenal Server Error"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return (JsonResult.serverError("Intenal Server Error"));
        }
    }

    @PostMapping("/api/timetable/new")
    public ResponseEntity<?> addTimetable(@RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.addNew(timetableReq));
    }

    @DeleteMapping("/api/timetable/delete/{id}")
    public ResponseEntity<?> deleteTimetable(@PathVariable Integer id) {
        return ResponseEntity.ok(timetableService.deleteById(id));
    }

    @GetMapping("/api/timetable/teacher-all/{id}")
    public ResponseEntity<?> getTeacherFromId(@PathVariable("id") Integer teacherId) {
        return ResponseEntity.ok(timetableService.getTeacherFromId(teacherId));
    }

    @GetMapping("/api/timetable/classroom-all/{id}")
    public ResponseEntity<?> getClassFromId(@PathVariable("id") Integer classId, @RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timeTableRepository.findByClassroomId(timetableReq.getClassroomId())
                .stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList())
        );
    }


    @PostMapping("/api/timetable/test/getList/{id}")
    public ResponseEntity<?> getListTeacherId(@PathVariable("id") Integer id, @RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.test(id, timetableReq));
    }

    // API For Student
    @GetMapping("/api/timetable/student/{account_id}")
    public ResponseEntity<?> getTimetableForStudent(@PathVariable("account_id") Integer accountId) {
        return ResponseEntity.ok(timetableService.getTimetableFromStudentId(accountId));
    }

}