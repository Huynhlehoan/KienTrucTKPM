package fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file1 = new File("Data.txt");
        File file2 = new File("Image.png");
        File file3 = new File("System.cfg");

        Folder folderSub = new Folder("SubFolder");
        folderSub.add(file1);
        folderSub.add(file2);

        Folder folderRoot = new Folder("Root");
        folderRoot.add(folderSub);
        folderRoot.add(file3);

        folderRoot.showDetails();
    }
}