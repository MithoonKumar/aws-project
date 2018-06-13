package com.example.awsproject.Controller;

import com.example.awsproject.Service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class AwsController {
    @Autowired
    private AmazonClient amazonClient;

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestPart(value = "filer") MultipartFile filer){
        return this.amazonClient.uploadFile(filer);
    }

    @DeleteMapping(value="/delete")
    @ResponseBody
    public String deleteFile(@RequestBody Map<String, String> body){
        return this.amazonClient.deleteFileFromS3Bucket(body.get("url"));
    }

    @GetMapping(value="/get")
    @ResponseBody
    public String getFile(@RequestParam("fileName") String fileName){
        amazonClient.fetchObject(fileName);
        return "successfully fetched";
    }


}


