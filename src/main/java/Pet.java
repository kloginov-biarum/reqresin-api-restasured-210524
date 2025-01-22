import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Pet {
  private long id;
  private Category category;
  private String name;
  private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Getter@Setter@NoArgsConstructor@AllArgsConstructor
public static class Category {
  private long id;
  private String name;
}
    @Getter@Setter@NoArgsConstructor@AllArgsConstructor
public static class Tag {
    private long id;
    private String name;
}

}
