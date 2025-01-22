import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class SuccessfulCreateUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
