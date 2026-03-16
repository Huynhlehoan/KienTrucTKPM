package fit.se.cms_layredarchitechture.service;

import fit.se.cms_layredarchitechture.entity.Post;
import fit.se.cms_layredarchitechture.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề không được để trống");
        }
        return postRepository.save(post);
    }
}