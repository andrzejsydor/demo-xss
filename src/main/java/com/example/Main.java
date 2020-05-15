package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
  public static final int BOUND = 1000000;
  public static final int DELAY = 0;
  public static final int PERIOD_3_SECONDS = 3000;
  public static final int PERIOD_10_SECONDS = 10000;
  public static final int PERIOD_30_SECONDS = 30000;
  public static Random RANDOM = new Random();
  private final String getUrl = "http://localhost:8080/";
  private final String addUrl = getUrl + "add";
  private final String deleteUrl = getUrl + "delete";
  private final String searchUrl = getUrl + "searchField";

  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    runGet();
    runPost();
    runDelete();
    runSearch();
  }

  private void runPost() {
    Timer timerPost = new Timer();
    timerPost.schedule(new PostTask(addUrl), DELAY, PERIOD_10_SECONDS);
  }

  private void runGet() {
    Timer timer = new Timer();
    timer.schedule(new GetTask(getUrl), DELAY, PERIOD_30_SECONDS);
  }

  private void runSearch() {
    Timer timer = new Timer();
    timer.schedule(new SearchTask(searchUrl), DELAY, PERIOD_30_SECONDS);
  }

  private void runDelete() {
    Timer timer = new Timer();
    timer.schedule(new DeleteTask(deleteUrl), DELAY, PERIOD_3_SECONDS);
  }
}

class GetTask extends TimerTask {
  private final String url;
  private final HttpClient httpClient;

  GetTask(String url) {
    this.url = url;
    httpClient = HttpClient.newHttpClient();
  }

  @Override
  public void run() {
    try {
      getMethod();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void getMethod() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response);
  }
}

class SearchTask extends TimerTask {
  private final String url;
  private final HttpClient httpClient;

  SearchTask(String url) {
    this.url = url;
    httpClient = HttpClient.newHttpClient();
  }

  @Override
  public void run() {
    try {
      searchMethod();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void searchMethod() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(getSearchUrlXssSometimes()))
        .timeout(Duration.ofMinutes(1))
        .header("Content-Type", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response);
  }

  private String getSearchUrlXssSometimes() {
    if (Main.RANDOM.nextInt(5) == 1) {
      return url + "?id=<script>alert(1)</script>";
    } else {
      return url + "?id=" + Main.RANDOM.nextInt(Main.BOUND);
    }
  }
}

class PostTask extends TimerTask {
  private final String url;
  private final HttpClient httpClient;

  PostTask(String url) {
    this.url = url;
    this.httpClient = HttpClient.newHttpClient();
  }

  @Override
  public void run() {
    try {
      postMethod();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void postMethod() throws IOException, InterruptedException {
    int nextInt = Main.RANDOM.nextInt(Main.BOUND);
    String body = getBodyWithXssSometimes(nextInt);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response);
  }

  private String getBodyWithXssSometimes(int nextInt) {
    if (Main.RANDOM.nextInt(5) == 1) {
      return "title=title " + nextInt + "&text=<script>alert(1)</script>";
    } else {
      return "title=title " + nextInt + "&text=text " + nextInt;
    }
  }
}

class DeleteTask extends TimerTask {
  private final String url;
  private final HttpClient httpClient;

  DeleteTask(String url) {
    this.url = url;
    httpClient = HttpClient.newHttpClient();
  }

  @Override
  public void run() {
    try {
      deleteMethod();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void deleteMethod() throws IOException, InterruptedException {
    String url = this.url + "id=" + Main.RANDOM.nextInt(Main.BOUND);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .DELETE()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response);
  }
}
