package com.shuojie.domain;

public class ExecuteResult {

    private Boolean status;
    private String message;

  public Boolean getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "ExecuteResult{" +
      "status=" + status +
      ", message='" + message + '\'' +
      '}';
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
