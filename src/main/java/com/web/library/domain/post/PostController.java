package com.web.library.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /** 게시글 작성 페이지
     * 새로운 값이 생성되는 경우는 id가 생성되어있지 않기 때문에 required=false
     *  기존 값 변경하는 경우에는 id를 파라미터로 받는다.
     */


    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if(id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        return "post/write";
    }

    // form 태그에 name 값을 통해 데이터를 컨트롤러 메서드로 전송
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
    }

    @GetMapping("/post/list.do")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params) {
        postService.updatePost(params);
        return "redirect:/post/list.do";
    }

    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id) {
        postService.deletePost(id);
        return "redirect:/post/list.do";
    }

}
