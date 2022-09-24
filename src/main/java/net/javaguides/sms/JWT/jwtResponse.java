package net.javaguides.sms.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
public class jwtResponse {
    private  String jwtToken;
}
