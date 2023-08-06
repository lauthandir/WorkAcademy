package pl.isa.controllers;

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
    return StudentsData.readStudentData();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveStudent(StudentModel student) {
    StudentsData.saveStudentData(student);
    return Response.status(Response.Status.CREATED).build();
    }
}
