package src;

public class FileSystem {
  private Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    root = new Directory("root", null);
    currentDirectory = root;
  }

  public Directory getRoot() { return root; }

  public void setRoot(final Directory root) { this.root = root; }

  public Directory getCurrentDirectory() { return currentDirectory; }

  public void setCurrentDirectory(final Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  public void setCurrentDirectory(final String path) throws IllegalArgumentException {
    final Directory newCurrentDirectory = findDirectory(path);
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

  public String[] splitPath(final String path) { return path.split("/"); }

  public Directory findDirectory(final String path) {
    return root.findDirectory(splitPath(path));
  }

  public void printCurrentChildren() { currentDirectory.printChildren(); }

  public void createFile(final String name) {
    final File newFile = new File(name, currentDirectory);
    currentDirectory.add(newFile);
  }

  public void createDirectory(final String name) {
    final Directory newDirectory = new Directory(name, currentDirectory);
    currentDirectory.add(newDirectory);
  }

  public void delete(final String name) { currentDirectory.delete(name); }

  public void move(final String name, final String path) {
    final FileSystemElement element = currentDirectory.find(name);
    if (element == null)
      throw new IllegalArgumentException("Element not found");
    final Directory newParent = findDirectory(path);
    if (newParent == null)
      throw new IllegalArgumentException("Directory not found");
    element.move(newParent);
  }

  public FileSystemElement recFind(final String name) { return root.recFind(name); }

  public void printTree() {
    System.out.println("Path to current directory: ");
    root.printTree(0);
  }
}
