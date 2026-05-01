    package com.example.practice1.dto.post;


    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class PostRequest {

        public String title;
        public String content;
        public String author;

        @Override
        public String toString() {
            return "CreatePostRequest{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", Author='" + author + '\'' +
                    '}';
        }
    }
