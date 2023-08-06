package pl.isa.controllers;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pl.isa.models.StudentModel;
import pl.isa.studentsdatabase.StudentsData;
import java.util.List;


@Path("/students")
public class StudentsController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentModel> getStudents() {
    return StudentsData.readStudentData(new Gson());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveStudent(StudentModel student) {
    StudentsData.saveStudentData(student);
    return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/averageAge")
    @Produces(MediaType.APPLICATION_JSON)
    public double getAverageAge() {
        return StudentsData.getAverageAge();
    }

    @GET
    @Path("/totalStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTotalStudents() {
        return StudentsData.getTotalStudents();
    }

    @GET
    @Path("/studentsInCourse/{course}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getStudentsInCourse(@PathParam("course") String course) {
        return StudentsData.getStudentsInCourse(course);
    }
}
