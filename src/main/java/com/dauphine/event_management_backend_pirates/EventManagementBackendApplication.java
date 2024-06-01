package com.dauphine.event_management_backend_pirates;

        import io.swagger.v3.oas.annotations.OpenAPIDefinition;
        import io.swagger.v3.oas.annotations.info.Contact;
        import io.swagger.v3.oas.annotations.info.Info;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Event Management backend",
                description = "Event Management endpoints and apis",
                version = "1.0.0"
        )
)
public class EventManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementBackendApplication.class, args);
    }
}
