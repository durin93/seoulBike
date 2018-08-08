package com.seoulbike.demo.domain;

import java.util.Objects;

public class Result {
    private static final String DEFAULT_URL = " ";
    private static final String USER_CREATE_FAIL_URL = "/users/form";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private String message;
    private String url;
    private String status;

    public Result() {
    }

    public Result(String url, String status) {
        this.url = url;
        this.status = status;
    }

    public Result(String url, String status, String message) {
        this.url = url;
        this.status = status;
        this.message = message;
    }

    public static Result ofSuccess() {
        return new Result(DEFAULT_URL,SUCCESS);
    }

    public static Result ofSuccess(String url) {
        return new Result(url,SUCCESS);
    }

    public static Result ofFailed() {
        return new Result(USER_CREATE_FAIL_URL, FAIL);
    }
    public static Result ofFailed(String message) {
        return new Result(USER_CREATE_FAIL_URL, FAIL, message);
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(url, result.url) &&
                Objects.equals(status, result.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, status);
    }

    @Override
    public String toString() {
        return "Result{" +
                "url='" + url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
