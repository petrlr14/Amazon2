package com.pdm2018.amazon2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Example {

        @SerializedName("message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

