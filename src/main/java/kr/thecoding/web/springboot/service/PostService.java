package kr.thecoding.web.springboot.service;

import kr.thecoding.web.springboot.domain.posts.Posts;
import kr.thecoding.web.springboot.domain.posts.PostsRepo;
import kr.thecoding.web.springboot.web.dto.PostsListResponseDto;
import kr.thecoding.web.springboot.web.dto.PostsResponseDto;
import kr.thecoding.web.springboot.web.dto.PostsSaveRequestDto;
import kr.thecoding.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepo postsRepo;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepo.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " data not found!!"));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id +  " data not found!!"));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepo.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(id + " : data not found!! "));
        postsRepo.delete(posts);
    }



}
