package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    public HackerNews(HttpClient client) {
        this.client = client;
    }

    private final HttpClient client;
    private static final HttpRequest TOP_STORIES_REQUEST = HttpRequest.newBuilder()
        .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
        .build();
    private static final Pattern JSON_PATTERN =
        Pattern.compile("\"([^\"]+)\":\"?([^\"]+)\"?[,}]");

    public long[] hackerNewsTopStories() {
        try {
            HttpResponse<String> response = client.send(TOP_STORIES_REQUEST, HttpResponse.BodyHandlers.ofString());

            String[] ids = response.body()
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");

            long[] result = new long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                result[i] = Long.parseLong(ids[i]);
            }

            return result;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // можно регуляркой сразу найти именно название, но так можно искать не только его
            Matcher matcher = JSON_PATTERN.matcher(response.body());
            while (matcher.find()) {
                if (matcher.group(1).compareTo("title") == 0) {
                    return matcher.group(2)
                        .replaceAll("}", "")
                        .replaceAll("}", "");
                }
            }
            return "";
        } catch (IOException | InterruptedException e) {
            return "";
        }

    }

}
