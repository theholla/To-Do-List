import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class TaskTest {

  @Test
  public void task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void task_instantiatesWithDescription_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals("Mow the lawn", myTask.getDescription());
  }

  // @Rule
  // public DatabaseRule database = new DatabaseRule();
  //
  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Task.all().size(), 0);
  // }

}
