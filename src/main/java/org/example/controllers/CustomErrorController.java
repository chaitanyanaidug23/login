package org.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController {

    @RequestMapping("/custom-error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("code", 404);
                model.addAttribute("message", "The page you are looking for might have been removed.");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("code", 500);
                model.addAttribute("message", "We are experiencing some problems. Please try again later.");
            } else {
                model.addAttribute("code", statusCode);
                model.addAttribute("message", "Unexpected error");
            }
        }
        return "error";  // This should be the name of the view (HTML or template) for displaying the error.
    }
}
