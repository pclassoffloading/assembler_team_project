import java.io.*;
public class output{

  public output()throws FileNotFoundException{
    //write_file("filename.txt");
    write_old_file("filename.txt");
	}
  public void write_old_file(String filename)
  {
    Writer writer = null;

    try {
        writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("filename.txt", true), "utf-8"));
        writer.append("\nSomething");
    } catch (IOException ex) {
      // report
    } finally {
       try {writer.close();} catch (Exception ex) {/*ignore*/}
    }
  }

}
