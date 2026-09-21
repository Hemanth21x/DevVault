package com.devvault.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Phase 22 — centralized exception handling.
 * Catches anything uncaught in any controller and shows a friendly page
 * instead of a raw stack trace.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAll(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error-page";
    }
}
