package ru.ho4upizzu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.ho4upizzu.exception.ObjectNotFoundException;
import ru.ho4upizzu.model.Post;
import ru.ho4upizzu.repository.PostRepository;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @RequestMapping("/posts")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("posts");
        modelAndView.addObject("posts", postRepository.findAllByOrderByPublishDateDesc());
        return modelAndView;
    }

    @RequestMapping("/posts/{id}")
    public ModelAndView findById(@PathVariable Integer id) {
        Post one = postRepository.findOne(id);
        if(one == null) {
            throw new ObjectNotFoundException("post.id=" + id);
        }

        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("post", one);
        return modelAndView;
    }

}
