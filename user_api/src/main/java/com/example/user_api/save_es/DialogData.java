package com.example.user_api.save_es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dialog_tv360_index")
public class DialogData {

    private String question;
    private Intent intent;
    private Answer[] answer;
    private String bot_id;
    private long question_at;
    private long answer_at;


    public static class Intent {
        private String id;
        private String label;
        private String name;

    }


    public static class Answer {
        private String type;
        private String text;

    }
}
