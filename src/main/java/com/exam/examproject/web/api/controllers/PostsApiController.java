package com.exam.examproject.web.api.controllers;

import com.exam.examproject.services.models.EditPostServiceModel;
import com.exam.examproject.services.models.PostServiceModel;
import com.exam.examproject.services.services.PostsService;
import com.exam.examproject.web.api.models.PostResponseModel;
import com.exam.examproject.web.view.models.EditPostViewModel;
import lombok.AllArgsConstructor;
import netscape.javascript.JSObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PostsApiController {
    private final PostsService postsService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/posts")
    public ResponseEntity<List<PostResponseModel>> getPosts(HttpSession session) {
        List<PostServiceModel> postServiceModels = this.postsService.getAllPosts();
        List<PostResponseModel> postResponseModels = postServiceModels.stream()
                .map(post -> this.modelMapper.map(post, PostResponseModel.class)).collect(Collectors.toList());
        return new ResponseEntity<>(postResponseModels, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/edit", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String,String>> editPost(@Valid @RequestBody EditPostViewModel jsonResponse, BindingResult bindingResult) {
        System.out.println(jsonResponse);
     Map<String,String> errors = new HashMap<>();
       if(bindingResult.hasErrors()){
           bindingResult.getFieldErrors().forEach(binding->errors.put(binding.getField(),binding.getDefaultMessage()));
      //   errors.add(bindingResult.getFieldErrors().iterator().next().getField()+ "  : "+bindingResult.getFieldErrors().iterator().next().getDefaultMessage());
     return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
       }
        EditPostServiceModel editPostServiceModel = this.modelMapper.map(jsonResponse, EditPostServiceModel.class);
        this.postsService.updatePost(editPostServiceModel);

        return new ResponseEntity<>(new HashMap<>(),HttpStatus.OK);
    }

}
