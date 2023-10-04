import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class UniversityParser {
    public static void main(String[] args) {
        try {
            // Загрузка XML-документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("University.xml"));

            // Получение списка факультетов
            NodeList facultyList = document.getElementsByTagName("faculty");
            for (int i = 0; i < facultyList.getLength(); i++) {
                Node facultyNode = facultyList.item(i);
                if (facultyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element facultyElement = (Element) facultyNode;
                    System.out.println("Faculty Name: " + facultyElement.getElementsByTagName("name").item(0).getTextContent());

                    // Получение списка курсов
                    NodeList courseList = facultyElement.getElementsByTagName("course");
                    for (int j = 0; j < courseList.getLength(); j++) {
                        Node courseNode = courseList.item(j);
                        if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element courseElement = (Element) courseNode;
                            System.out.println("  Course Name: " + courseElement.getElementsByTagName("name").item(0).getTextContent());
                            System.out.println("  Professor: " + courseElement.getElementsByTagName("professor").item(0).getTextContent());
                        }
                    }
                }
            }

            // Получение списка студентов
            NodeList studentList = document.getElementsByTagName("student");
            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;
                    System.out.println("Student Name: " + studentElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Faculty ID: " + studentElement.getElementsByTagName("faculty_id").item(0).getTextContent());

                    // Получение списка курсов, которые посещает студент
                    NodeList courseList = studentElement.getElementsByTagName("course_id");
                    System.out.print("Courses: ");
                    for (int j = 0; j < courseList.getLength(); j++) {
                        Node courseNode = courseList.item(j);
                        if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.print(courseNode.getTextContent() + " ");
                        }
                    }
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
