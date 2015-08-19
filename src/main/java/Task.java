

public class Task {
  //initializing the member variable into the class, saved into the object itseld
  String mDescription;

  public Task(String description) {
    //inside of the constructor, we call mDesc = desc
    mDescription = description;
  }

  public String getDescription() {
    return mDescription;
  }
}
