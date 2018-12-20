package com.lin.comm.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author meilin
 * @Date 2018/12/314:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/validation")
@Validated
public class ValidationController {
    @PostMapping("/test")
    public String test(@Validated @RequestBody DemModel demModel,@NotEmpty(message = "userName不能为空") String userName){
        System.out.println(demModel.toString());
        System.out.println(userName);
        return "OK";
    }


}
