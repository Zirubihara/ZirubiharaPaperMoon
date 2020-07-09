package com.zirubihara.phototraveller.phototraveller.Advice;

import com.zirubihara.phototraveller.phototraveller.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuestNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String PostNotFoundHandler(PostNotFoundException ex){ return ex.getMessage();}
}
