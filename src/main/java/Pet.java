import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class Pet {
  private long id;
  private Category category;
  private String name;
  private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Getter@Setter
public static class Category {
  private long id;
  private String name;
}
    @Getter@Setter
public static class Tag {
    private long id;
    private String name;
}

}
