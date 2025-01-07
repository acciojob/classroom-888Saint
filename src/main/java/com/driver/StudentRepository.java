package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        if (student != null && student.getName() != null)
        {
            studentMap.put(student.getName(), student);
        }
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        if (teacher != null && teacher.getName() != null)
        {
            teacherMap.put(teacher.getName(), teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            teacherStudentMapping.computeIfAbsent(teacher, k -> new ArrayList<>()).add(student);

            Teacher t = teacherMap.get(teacher);
            t.setNumberOfStudents(t.getNumberOfStudents() + 1);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.getOrDefault(student, null);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.getOrDefault(teacher, null);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> findAllStudents(){
        // your code goes here
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        if (teacherMap.containsKey(teacher))
        {
            teacherMap.remove(teacher);

            List<String> students = teacherStudentMapping.remove(teacher);

            if (students != null)
            {
                for (String student : students)
                {
                    studentMap.remove(student);
                }
            }

        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherMap.clear();

        for (List<String> students : teacherStudentMapping.values())
        {
            for (String student : students)
            {
                studentMap.remove(student);
            }
        }
        teacherStudentMapping.clear();
    }
}