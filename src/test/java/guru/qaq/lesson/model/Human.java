package guru.qaq.lesson.model;

import java.util.List;

public class Human {

//  {
//    "name": "Fedya",
//    "age": 29,
//    "hobby": [
//      "speaking",
//      "teaching"
//  ],
//    "passport": {
//    "number": 1231233,
//      "issuer": "МВД"
//  }
//  }
  
  public String name;
  public Integer age;
  public List<String> hobby;
  public Passport passport;
  
  
  
  
  public String getName() {
    return name;
  }
  

  public Integer getAge() {
    return age;
  }
  
  public List<String> getHobby() {
    return hobby;
  }
  
  public Passport getPassport() {
    return passport;
  }
  

}
