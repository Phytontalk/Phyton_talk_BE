package like_lion.phytontalk.member.dto;

import java.time.LocalDate;

public record SignupRequest(String name, String email, String password, String sns, String birthDate) {
}
