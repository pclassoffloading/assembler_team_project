import java.io.*;
import java.io.File; //boolean createNewFile() This method atomically creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
import java.util.Scanner;
import java.util.StringTokenizer;
public class input{
	File file1; int srcln_count = 0;
	Scanner fileLines;Scanner fileWords;
	Source_line source_lines[];

	public input(String fileName) throws IOException{

		initialize_vars(fileName);
		readEachLine(this.fileLines);
		//user will call provide_source_lines();
	}

	public void readEachLine(Scanner fileInput1)throws IOException{
		while (fileInput1.hasNext()){
			readEachWord(fileInput1.nextLine());
		}
		fileInput1.close();
	}

	public void readEachWord(String strLine) throws IOException{
		this.fileWords = new Scanner(strLine).useDelimiter("\\s+");
		int total_line_count = count_words(fileWords);

		restart_string_scanner(fileWords, strLine);
		build_sourcelines(fileWords, total_line_count);
		restart_string_scanner(fileWords, strLine);
	}

	public void initialize_vars(String fileName)throws IOException{
		this.file1 = new File(fileName);
		this.fileLines = new Scanner(file1);
		this.source_lines= new Source_line[countFile(fileLines)];
		restart_file_scanner(fileLines);
	}

	public void restart_file_scanner(Scanner fileLines)throws IOException{
		this.fileLines.close(); this.fileLines = new Scanner(file1);
	}

	public int countFile(Scanner fileInput0){
		int count = 0;
		while (fileInput0.hasNext()){
			fileInput0.nextLine();
			count++;
		}
		return count;
	}

	public int count_words(Scanner fileWords)throws IOException{
		boolean flag = false;
		int count = -1;
		while (this.fileWords.hasNext()){
			String word = this.fileWords.next();
			if(word.charAt(0) == '.'){flag = true;}
			if(flag == true) { continue;}
			//System.out.printf("Line who knows! WORD %d%s\n", count, word);
			count++;
		}
		return count;
	}

	public void restart_string_scanner(Scanner fileWords, String strLine)throws IOException{
		this.fileWords.close(); this.fileWords = new Scanner(strLine).useDelimiter("\\s+");
	}

	public int build_sourcelines(Scanner s, int total_line_count){
		boolean flag = false;
		while(total_line_count >= 3){
			total_line_count--;
		}
		//System.out.println(total_line_count);
		int word_numb = 0;
		source_lines[srcln_count] = new Source_line();
		while (this.fileWords.hasNext()){
			String word = this.fileWords.next();
			if(word.charAt(0) == '.'){flag = true;}
			if(flag == true) {source_lines[srcln_count].add_To_Note(word); continue;}
			//System.out.printf("Line who knows! WORD %d%s\n", word_numb, word);
			source_lines[srcln_count].add_word(total_line_count, word_numb, word);

			//source_lines[srcln_count].tell_source_line();
			//System.out.printf("Line who knows! WORD %d%s\n", count, word);
			word_numb++;
		}

		//source_lines[srcln_count].tell_source_line();
		this.srcln_count++;
		return word_numb;
	}

	public Source_line[] provide_source_lines(){
		return this.source_lines;
	}
}
