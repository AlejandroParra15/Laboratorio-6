package CustomsExceptions;

@SuppressWarnings("serial")
public class CVSFileNotFoundException extends Exception{
	public CVSFileNotFoundException() {
		super("The file has not been loaded succesfylly");
	}
}
