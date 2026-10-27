package HospitalManagement.Application.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    private int status;
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private T data;
}
