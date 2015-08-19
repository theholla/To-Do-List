import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("task", request.session().attribute("task"));

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    //first time, list is null
    ArrayList<Task> tasks = request.session().attribute("tasks"); ///////////Tasks?

    if (tasks == null) {
      tasks = new ArrayList<Task>();
      request.session().attribute("tasks", tasks);
    }

    String description = request.queryParams("description");
    Task newTask = new Task(description);

//put new task into new array list
    tasks.add(newTask);

    model.put("template", "templates/success.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
