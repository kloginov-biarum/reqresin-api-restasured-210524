import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor// All und No - чтобы не было проблем с обьектами и конструктором

public class CreateUserRequest {
    private String name;
    private String job;

}