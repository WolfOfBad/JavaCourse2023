package edu.hw6;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class HackerNewsTest {
    @Mock
    HttpResponse<String> response;
    @Mock
    HttpClient client;

    @Test
    @DisplayName("hackerNewsTopStories()")
    public void topStoriesTest() throws IOException, InterruptedException {
        doReturn("[1,2,3]").when(response).body();
        doReturn(response).when(client).send(any(), any());

        HackerNews obj = new HackerNews(client);

        long[] result = obj.hackerNewsTopStories();

        assertThat(result[0]).isEqualTo(1);
        assertThat(result[1]).isEqualTo(2);
        assertThat(result[2]).isEqualTo(3);
    }

    @Test
    public void storyNameTest() throws IOException, InterruptedException {
        doReturn("{\"title\":\"test name\"}").when(response).body();
        doReturn(response).when(client).send(any(), any());

        HackerNews obj = new HackerNews(client);

        String result = obj.news(37570037);

        assertThat(result).isEqualTo("test name");
    }
}
