package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportValidator {
    private static List<String> vaildate(Report r) {
        List<String> errors = new ArrayList<String>();

        String title_error = _vaildateTitle(r.getTitle());
        if(!title_error.equals("")) {
            errors.add("title_error");
        }

        String content_error = _vaildateContent(r.getContent());
        if(!content_error.equals("")) {
            errors.add("content_error");
        }

        return errors;
    }

    private static String _vaildateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
        }

        return "";
    }

    private static String _vaildateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
        }

        return "";
    }

}
