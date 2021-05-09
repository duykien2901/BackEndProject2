package com.example.project2.Json;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class JsonResult {
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private String result;
    private Object data;
    private Integer total;

    private static JsonResult build(String result, Object data, Integer totalPage) {
        return new JsonResult(result, data, totalPage);
    }


    public static ResponseEntity<JsonResult> success(Object data, Integer totalPage) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(CONTENT_TYPE)).body(JsonResult.build("success", data, totalPage));
    }

    public static ResponseEntity<JsonResult> found(Object data, Integer totalePage) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(CONTENT_TYPE)).body(JsonResult.build("found", data, totalePage));
    }

    public static ResponseEntity<JsonResult> notFound(Object data) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.valueOf(CONTENT_TYPE)).body(JsonResult.build("not found", data + " not exits", null));
    }

    public static ResponseEntity<JsonResult> serverError(Object data) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.valueOf(CONTENT_TYPE)).body(JsonResult.build("error", data, null));
    }

    public static ResponseEntity<JsonResult> idNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.valueOf(CONTENT_TYPE)).body(JsonResult.build("Not found", "id is not exits", null));
    }

}
