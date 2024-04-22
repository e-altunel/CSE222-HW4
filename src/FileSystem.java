package src;

public class FileSystem {
  private Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    root = new Directory("root", null);
    currentDirectory = root;
  }

  public Directory getRoot() { return root; }

  public void setRoot(Directory root) { this.root = root; }

  public Directory getCurrentDirectory() { return currentDirectory; }

  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public void setCurrentDirectory(String path) throws IllegalArgumentException {
    Directory newCurrentDirectory = findDirectory(path);
    if (newCurrentDirectory == null)
      throw new IllegalArgumentException("Directory not found");
    currentDirectory = newCurrentDirectory;
  }

  public void printCurrentPath() {
    if (currentDirectory == root) {
      System.out.println("/");
      return;
    }
    currentDirectory.printPath();
    System.out.println();
  }

  public String[] splitPath(String path) { return path.split("/"); }

  public Directory findDirectory(String path) {
    return root.findDirectory(splitPath(path));
  }

  public void printCurrentChildren() { currentDirectory.printChildren(); }

  public void createFile(String name) {
    File newFile = new File(name, currentDirectory);
    currentDirectory.add(newFile);
  }

  public void createDirectory(String name) {
    Directory newDirectory = new Directory(name, currentDirectory);
    currentDirectory.add(newDirectory);
  }

  public void delete(String name) { currentDirectory.delete(name); }

  public void move(String name, String path) {
    FileSystemElement element = currentDirectory.find(name);
    if (element == null)
      throw new IllegalArgumentException("Element not found");
    Directory newParent = findDirectory(path);
    if (newParent == null)
      throw new IllegalArgumentException("Directory not found");
    element.move(newParent);
  }

  public FileSystemElement recFind(String name) { return root.recFind(name); }

  public void printTree() {
    System.out.println("Path to current directory: ");
    root.printTree(0);
  }
}
