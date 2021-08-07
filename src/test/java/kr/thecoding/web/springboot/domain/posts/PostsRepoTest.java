package kr.thecoding.web.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepoTest {
    @Autowired
    PostsRepo postsRepo;

    @After
    public void ceanup(){
        postsRepo.deleteAll();
    }

    @Test
    public void getList(){
        String title = "test title";
        String content = "test content";
        postsRepo.save(Posts.builder()
                .title(title)
                .content(content)
                .author("hong")
                .build()
        );

        List<Posts> postsList = postsRepo.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}