package com.task.cards.controller;

import com.task.cards.config.HttpSessionConfig;
import com.task.cards.xmlLogic.Employee;
import com.task.cards.xmlLogic.EmployeeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class mainController {


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {

        request.getSession(); //создаем сессию если еще не создана или умерла
        return "home";
    }
    @GetMapping("/sessions")
    public String home(Model model) {

        model.addAttribute("onlineCounter", HttpSessionConfig.onlineCounter.intValue()); //счетчики онлайн и общий
        model.addAttribute("allRequestCounter", HttpSessionConfig.allRequestCounter.intValue());
        return "sessions";
    }
    @PostMapping("/result")
    public String upload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) throws IOException {

        if (file!=null){
            request.getSession(); //создаем сессию если еще не создана или умерла
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            File fileXML = new File(uploadPath + "/" + resultFilename);
            file.transferTo(new File(uploadPath + "/" + resultFilename));


            if (EmployeeHandler.validateXMLByXSD(fileXML, new File(uploadPath + "/format.xsd"))) //Валидация по XSD
            {
                try {

                    ArrayList<Employee> employees = EmployeeHandler.makeTableFromXML(new File(uploadPath + "/" + resultFilename));
                    model.addAttribute("employees", employees);
                    model.addAttribute("employees_count", employees.size());

                }
                catch (Exception e){
                    model.addAttribute("error", "Что-то пошло не так...");
                    return "error";
                }


                return "upload";
            }
            else{ //файл не соотвествует схеме
                model.addAttribute("error", "Файл не соответствует формату. Проверьте ваш файл на соответствие <a href='/xml/format.xsd'>XSD</a>");
            }


        }

        return "error";

    }
}
