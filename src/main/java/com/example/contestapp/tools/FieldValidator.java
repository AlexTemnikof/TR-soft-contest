package com.example.contestapp.tools;

public class FieldValidator {

    public static Integer validateId(String id) throws Exception {
        try {
            return Integer.parseInt(id);
        }
        catch (Exception e){
            throw new Exception("wrong id format");
        }
    }

    public static void validateBirthday(){
        //todo: implement by regex maybe
    }

    public static void validateEmail(){
        //todo: implement by regex maybe
    }

    public static void validatePhoneNumber(){
        //todo: implement
    }
}
