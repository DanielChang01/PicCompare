package com.yuan.controller;

import org.apache.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuan.utils.JsonResponseBody;
import com.yuan.utils.JsonResult;

/**
 * Created by danielchang on 2018/2/1.
 */
@Controller
@RequestMapping("/yuan/pic")
public class PictureController {

    @RequestMapping(value = "getUpload",method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse getAndUploadFile(HttpRequest request, HttpResponse response){

        return null;
    }

//    @RequestMapping(value = "getUpload",method = RequestMethod.POST)
//    @JsonResponseBody
//    public JsonResult<String> uploadFile(HttpServletRequest request){
//
//        return JsonResult.success("success");
//    }


}
