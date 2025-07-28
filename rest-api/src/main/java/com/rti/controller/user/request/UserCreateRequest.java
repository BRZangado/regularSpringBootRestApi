package com.rti.controller.user.request;

import com.rti.controller.validator.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @Size(min = 4)
    private String name;

    @Email
    private String email;

    @NotEmpty
    private String password;
}
