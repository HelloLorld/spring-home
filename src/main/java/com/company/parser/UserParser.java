package com.company.parser;

import com.company.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Scanner;

public class UserParser {
    final String PATH = "src/main/resources/users.json";
    final String UPLOAD_PATH = "src/main/resources/uploaded/";

    public void parseToJson(User user) throws IOException, ParseException {
        JSONObject usersJSON = getJsonUsers(user);
        File file = new File(PATH);
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(usersJSON.toJSONString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public User parseFromJson(String name) throws IOException, ParseException {
        JSONObject users = getUsersObject();
        Object obj = users.get(name);
        if (obj != null) {
            JSONObject user = (JSONObject) obj;
            String fullName = (String) user.get("fullName");
            int age = ((Long) user.get("age")).intValue();
            int salary = ((Long) user.get("salary")).intValue();
            String email = (String) user.get("email");
            String job = (String) user.get("job");
            return new User(fullName, age, salary, email, job);
        }
        else return null;
    }

    public boolean getUserFromFile(MultipartFile file) throws IOException, ParseException {
        uploadFile(file);
        User user = readUploadedFile(file);
        if (user != null) {
            parseToJson(user);
            return true;
        }
        else return false;
    }

    public void deleteUploadedFile(MultipartFile file) {
        File uploadedFile = new File(UPLOAD_PATH + file.getOriginalFilename());
        if (uploadedFile.delete()) System.out.println("Файл был удален");
        else System.out.println("Файл не удалось удалить");
    }

    private JSONObject getJsonUsers(User user) throws IOException, ParseException {
        JSONObject users = getUsersObject();
        JSONObject userJSON = new JSONObject();
        userJSON.put("fullName",user.getFullName());
        userJSON.put("age",user.getAge());
        userJSON.put("salary",user.getSalary());
        userJSON.put("email",user.getEmail());
        userJSON.put("job",user.getJob());
        users.put(user.getFullName(),userJSON);
        return users;
    }

    private JSONObject getUsersObject() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject users;
        if (!fileIsEmpty(PATH))
            users = (JSONObject) jsonParser.parse(new FileReader(PATH));
        else users = new JSONObject();
        return users;
    }

    private boolean fileIsEmpty(String path) {
        File file = new File(path);
        return file.length()==0;
    }

    private User readUploadedFile(MultipartFile file) throws IOException {
        File uploadedFile = new File(UPLOAD_PATH + file.getOriginalFilename());
        FileReader fileReader = new FileReader(uploadedFile);
        Scanner scanner = new Scanner(fileReader);
        String infoUser = scanner.nextLine();
        scanner.close();
        String[] propertyOfUser = infoUser.split("/");
        if (propertyOfUser.length != 5) return null;
        else {
            try {
            return new User(propertyOfUser[0],Integer.parseInt(propertyOfUser[1]),
                    Integer.parseInt(propertyOfUser[2]), propertyOfUser[3], propertyOfUser[4]);}
            catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void uploadFile(MultipartFile file) throws IOException {
        File uploadedFile = new File(UPLOAD_PATH + file.getOriginalFilename());
        uploadedFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(uploadedFile);
        fout.write(file.getBytes());
        fout.flush();
        fout.close();
    }
}
