//package com.eventmanagement.eventapp.services;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//
//@Component
//public class FileService {
//    private final String path = "/eventImages";
//
//    public String saveFile(MultipartFile file , String fileName) throws IOException {
//        if(file == null || file.isEmpty()){
//            return "";
//        }
//
//        //String extension = file.getContentType().split("/")[1];
//
//        Path targetDirectory = Path.of(path);
//        if (!targetDirectory.toFile().exists()) {
//            Files.createDirectories(targetDirectory);
//        }
//        Path target = targetDirectory.resolve(fileName);
//
//        Files.copy(file.getInputStream() , target , StandardCopyOption.REPLACE_EXISTING);
//        System.out.println(target.toString());
//        return target.toString();
//    }
//}
package com.eventmanagement.eventapp.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class FileService {
//    private final String path = "target/classes/static/eventImages";

    public String saveFile(MultipartFile file, String fileName) throws IOException {
        if (file == null || file.isEmpty()) {
            return "";
        }

        Path targetDirectory = Path.of("target/classes/static/eventImages");
        if (!targetDirectory.toFile().exists()) {
            Files.createDirectories(targetDirectory);
        }

        Path target = targetDirectory.resolve(fileName);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return "/eventImages/" + fileName;
    }

}
