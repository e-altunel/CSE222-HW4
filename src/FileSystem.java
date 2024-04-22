package src;

/**
 * Represents a file system.
 */
public class FileSystem {
  /**
   * The root directory of the file system.
   */
  private Directory root;
  /**
   * The current directory of the file system.
   */
  private Directory currentDirectory;

  /**
   * Constructs a new FileSystem object with the root directory set to a new
   * Directory object with the name "root" and the parent directory set to null.
   */
  public FileSystem() {
    root = new Directory("root", null);
    currentDirectory = root;
  }

  /**
   * Returns the root directory of the file system.
   * @return the root directory
   */
  public Directory getRoot() { return root; }

  /**
   * Sets the root directory of the file system.
   * @param root the root directory
   */
  public void setRoot(final Directory root) { this.root = root; }

  /**
   * Returns the current directory of the file system.
   * @return the current directory
   */
  public Directory getCurrentDirectory() { return currentDirectory; }

  /**
   * Sets the current directory of the file system.
   * @param currentDirectory the current directory
   */
  public void setCurrentDirectory(final Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  /**
   * Sets the current directory of the file system to the directory with the
   * specified path.
   * @param path the path of the directory to set as the current directory
   * @throws IllegalArgumentException if the directory is not found
   */
  public void setCurrentDirectory(final String path)
      throws IllegalArgumentException {
    final Directory newCurrentDirectory = findDirectory(path);
    if (newCurrentDirectory == null)
      throw new IllegalArgumentException("Directory not found");
    currentDirectory = newCurrentDirectory;
  }

  /**
   * Prints the path to the current directory.
   */
  public void printCurrentPath() {
    if (currentDirectory == root) {
      System.out.println("/");
      return;
    }
    currentDirectory.printPath();
    System.out.println();
  }

  /**
   * Splits the specified path into parts.
   * @param path the path to split
   * @return the parts of the path
   */
  public String[] splitPath(final String path) { return path.split("/"); }

  /**
   * Finds the directory with the specified path.
   * @param path the path of the directory to find
   * @return the found directory, or null if not found
   */
  public Directory findDirectory(final String path) {
    return root.findDirectory(splitPath(path));
  }

  /**
   * Prints the children of the current directory.
   */
  public void printCurrentChildren() { currentDirectory.printChildren(); }

  /**
   * Creates a new file with the specified name in the current directory.
   * @param name the name of the file to create
   */
  public void createFile(final String name) {
    final File newFile = new File(name, currentDirectory);
    currentDirectory.add(newFile);
  }

  /**
   * Creates a new directory with the specified name in the current directory.
   * @param name the name of the directory to create
   */
  public void createDirectory(final String name) {
    final Directory newDirectory = new Directory(name, currentDirectory);
    currentDirectory.add(newDirectory);
  }

  /**
   * Deletes the file or directory with the specified name from the current
   * directory.
   * @param name the name of the file or directory to delete
   */
  public void delete(final String name) { currentDirectory.delete(name); }

  /**
   * Moves the file or directory with the specified name to the specified path.
   * @param name the name of the file or directory to move
   * @param path the path to move the file or directory to
   */
  public void move(final String name, final String path) {
    final FileSystemElement element = currentDirectory.find(name);
    if (element == null)
      throw new IllegalArgumentException("Element not found");
    final Directory newParent = findDirectory(path);
    if (newParent == null)
      throw new IllegalArgumentException("Directory not found");
    element.move(newParent);
  }

  /**
   * Recursively finds a file or directory with the specified name in the file
   * system.
   * @param name the name of the file or directory to find
   * @return the found file or directory, or null if not found
   */
  public FileSystemElement recFind(final String name) {
    return root.recFind(name);
  }

  /**
   * Prints the file system tree.
   */
  public void printTree() {
    System.out.println("Path to current directory: ");
    root.printTree(0);
  }
}
