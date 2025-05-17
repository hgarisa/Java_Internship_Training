package Java_Generics.ForLoop;

import java.util.*;

public class CourseDemo
{

    public static void main(String[] args) {
        // Example 1: List of Courses
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("CS101", "Intro to Programming"));
        courseList.add(new Course("CS102", "Data Structures"));

        System.out.println("Course List:");
        for (Course course : courseList) {
            System.out.println(course);
        }

        // Example 2: Set of Courses
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(new Course("MA101", "Calculus I"));
        courseSet.add(new Course("PH101", "Physics I"));

        System.out.println("\nCourse Set:");
        for (Course course : courseSet) {
            System.out.println(course);
        }

        // Example 3: Map of Course Code → Course
        Map<String, Course> courseMap = new HashMap<>();
        courseMap.put("ENG101", new Course("ENG101", "English Literature"));
        courseMap.put("HIS101", new Course("HIS101", "World History"));

        System.out.println("\nCourse Map (key & value):");
        for (String courseCode : courseMap.keySet()) {
            Course course = courseMap.get(courseCode);
            System.out.println(courseCode + ": " + course);
        }

        System.out.println("\nCourse Map (values only):");
        for (Course course : courseMap.values()) {
            System.out.println(course);
        }
    }
}
